package com.hengyuan.hicash.service.query;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.CustTempApplyPicQuery;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.user.YiDaoMarkService;
import com.hengyuan.hicash.entity.user.CustCustomer;
import com.hengyuan.hicash.entity.user.CustTempApplyPicEntity;
import com.hengyuan.hicash.exception.UploadPicException;
import com.hengyuan.hicash.parameters.request.user.YiDaoMarkReq;
import com.hengyuan.hicash.parameters.response.user.YiDaoMarkResp;
import com.hengyuan.hicash.service.validate.query.YiDaoMarkVal;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

import sun.misc.BASE64Encoder;

/**
 * 人脸识别Api，server调用易道或者face的api
 * resultCode，非通信失败都返回1
 * 修改：8月迭代，用户个人身份证头像自己从服务器获取
 * @author 0493
 * @createDate 2017-07-15
 *
 */

@WebServlet("/YiDaoMark")
public class YiDaoMark extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(YiDaoMark.class);
	static BASE64Encoder encoder = new BASE64Encoder();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		request.setCharacterEncoding("utf-8");

		YiDaoMarkService service = new YiDaoMarkService();
		YiDaoMarkResp resp = new YiDaoMarkResp();
		// 系统正常
		resp.setResultCode(ResultCodes.NORMAL);
		resp.setSavePic("Y");// 是否保存人脸照片，默认保存
		resp.setIs_passed(false);// 默认人脸识别不通过

		// 请求参数
		YiDaoMarkReq req = new YiDaoMarkReq();
		
		// face需要的文件，前端传入图片放入集合
		Map<String, String> faceFileMap = new HashMap<String, String>();

		try {
			
	
			// 获取前段form参数
			if (ServletFileUpload.isMultipartContent(request)) {

				DiskFileItemFactory dff = new DiskFileItemFactory();// 创建该对象
				dff.setSizeThreshold(1024000);// 指定在内存中缓存数据大小,单位为byte
				ServletFileUpload sfu = new ServletFileUpload(dff);// 创建该对象
				sfu.setFileSizeMax(5000000);// 指定单个上传文件的最大尺寸
				sfu.setSizeMax(10000000);// 指定一次上传多个文件的 总尺寸
				// 表单参数集合
				List<?> items = null;
				try {
					items = sfu.parseRequest(request);
				} catch (FileUploadException e) {
					logger.error("上传图片异常", e);
					RecordUtils.writeResponse(logger, null, resp);
					throw new UploadPicException();
				}
				// 上传的文件名称
				String fileName = "";

				// 遍历表单数据
				Iterator iter = items.iterator();
				while (iter.hasNext()) {

					FileItem item = (FileItem) iter.next();

					if (item.isFormField()) {// 表单数据，非文件

						String name = item.getFieldName(); // 表单名称

						if ("id_no".equals(name)) {// 获取身份证
							req.setId_no(item.getString());
						}
						if ("which_part".equals(name)) {// 获取人脸识别方
							req.setWhich_part(item.getString());
						}

//						if ("app_no".equals(name)) {// 获取正式单号
//							req.setApp_no(item.getString());
//							
//						}
						if ("delta".equals(name)) {
							req.setDelta(item.getString());
						}
						if("real_name".equals(name)){
							
							if(!StringUtils.isEmpty(item.getString())){
						     String utfName=new String(item.getString().getBytes(
									"ISO-8859-1"), "UTF-8");
							req.setReal_name(utfName);
							}else{
								logger.info("face人脸识别姓名为空");
							}
						}
						if ("user_name".equals(name)) {
							req.setUser_name(item.getString());
							
						}
						if ("temp_no".equals(name)) {// 获取临时单号
						req.setTemp_no(item.getString());
						
					}
					} else if (!item.isFormField()) {// 表单数据， 文件
						String paramName = item.getFieldName();// 参数名称
						fileName = item.getName();// 文件名称
						try {
							
							// 如果上传图片非易道并且不为空,个人头像照也不要，因为自己去服务器获取
							if (!paramName.equals("yidao_pic1") && !paramName.equals("yidao_pic2")&&!(paramName.equals("image_ref1"))
									&& !StringUtils.isEmpty(fileName)) {
								// 临时保存到本地，再发送到face
								String filePath ="";
								
								try{
									
								// 获取保存图片的url
								String uploadPath = request.getSession().getServletContext().getRealPath("/")
										+ ResourceUtils.getValue("FACE_PIC") + DateUtils.getDateStrByFlow2(new Date());
								// 如果目录不存在则创建
								File big = new File(uploadPath);
								if (!big.exists()) {
									big.mkdirs();
								}
								
								 filePath = uploadPath + File.separator + fileName;
								File storeFile = new File(filePath);
								// 在控制台输出文件的上传路径
								logger.info("用户：" + req.getUser_name() + "参数," + paramName + "上传文件路径：" + filePath);
								// 保存文件到硬盘
								item.write(storeFile);
								
								}catch(Exception e){
									logger.info("用户：" + req.getUser_name() +"获取face图片异常：",e);
									RecordUtils.writeResponse(logger, null, resp);	
								}
								
								// 文件路径放入集合，供faceApi使用
								faceFileMap.put(paramName, filePath);
                               
							}
							if ("yidao_pic1".equals(paramName)) {// 易道图片1

								InputStream stream1 = item.getInputStream();
								byte[] data11 = null;
								ByteArrayOutputStream output1 = new ByteArrayOutputStream();
								byte[] buf = new byte[1024];
								int numBytesRead = 0;
								while ((numBytesRead = stream1.read(buf)) != -1) {
									output1.write(buf, 0, numBytesRead);
								}

								data11 = output1.toByteArray();
								output1.close();
								stream1.close();
								String str1 = encoder.encodeBuffer(data11).trim();
								req.setYidao_pic1(str1);
							}
//个人头像照自己获取，所以易道的yidao_pic2是自己获取
//							if ("yidao_pic2".equals(paramName)) {// 易道图片2
//
//								InputStream stream2 = item.getInputStream();
//								byte[] data2 = null;
//								ByteArrayOutputStream output = new ByteArrayOutputStream();
//								byte[] buf = new byte[1024];
//								int numBytesRead = 0;
//								while ((numBytesRead = stream2.read(buf)) != -1) {
//									output.write(buf, 0, numBytesRead);
//								}
//								data2 = output.toByteArray();
//								String str2 = encoder.encodeBuffer(data2).trim();
//								req.setYidao_pic2(str2);
//
//							}
						
						} catch (Exception e) {
							logger.error(req.getUser_name() + "人脸识别人脸识别解析上传参数，失败" + paramName, e);
//							resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
							RecordUtils.writeResponse(logger, null, resp);
						}
					}
				}

				
			} else {
				logger.info("用户：" +req.getUser_name() + "人脸识别未上传图片");

			}
		   //-----------------------------------------根据用户名查询个人头像照片  begin
			String userName=req.getUser_name();	
			//查询个人头像照
			CustTempApplyPicQuery picQuery=new CustTempApplyPicQuery();
			CustTempApplyPicEntity tempPic= picQuery.queryNewPic(req.getUser_name(),"ZL168");//个人头像是必须传的，不用判断为空

			  //获取用户个人头像照 
				String personHeadUrl=request.getSession().getServletContext().getRealPath("/") +tempPic.getPicPath(); 
						
						logger.info("用户：" + userName+ "人脸识别个人头像照地址："+personHeadUrl);
						
						//server查询身份证姓名：
						CustcustomerQuery customerQuery = new CustcustomerQuery();
						CustCustomer faceCustomer= customerQuery.queryCustCustomer(userName);
						if(null==faceCustomer){
//							resp.setResultCode(ResultCodes.CUSTOMER_NOT_FOUNT);
							logger.info(userName + "客户信息不存在" );
						}else{

							if(StringUtils.isEmpty(req.getReal_name())){
							req.setReal_name(faceCustomer.getRealName());
							}
						}
					//自己获取个人头像给易道	
						String yidaoPic2="";
						 File f = new File(personHeadUrl);          
					        BufferedImage bi;   
					        try {   
					            bi = ImageIO.read(f);   
					            ByteArrayOutputStream baos = new ByteArrayOutputStream();   
					            ImageIO.write(bi, "jpg", baos);   
					            byte[] bytes = baos.toByteArray();   
					               
					            yidaoPic2= encoder.encodeBuffer(bytes).trim();   
					        } catch (IOException e) {   
					            logger.error(userName + ",获取个人头像照给易道赋值异常",e );
					        }  
					        
				req.setYidao_pic2(yidaoPic2);   			
			 //自己获取用户头像照给face
			faceFileMap.put("image_ref1", personHeadUrl);
			
	//-----------------------------------------根据用户名查询个人头像照片  end	
			
			
		} catch (Exception e) {
			logger.error("用户：" +req.getUser_name() + "人脸识别人脸识别失败", e);
			RecordUtils.writeResponse(logger, null, resp);
//			resp.setResultCode(ResultCodes.YIDAO_MARK_ERROR);
		}

		// face需要的所有图片，赋值
		req.setFileMap(faceFileMap);

		if (resp.getResultCode().equals(ResultCodes.NORMAL)) {// 如果从前端获取参数成功

			req.setUuid(UUID.randomUUID().toString());

			RecordUtils.writeRequest(logger, request, req);
			YiDaoMarkVal val = new YiDaoMarkVal(req);
			String result = val.validate();

			if (!ResultCodes.NORMAL.equals(result)) {

				resp.setResultCode(result);
				/* 获取返回中文信息 */
				String resuMsg = ResourceUtils.getString(result);
				resp.setResultMsg(resuMsg);
				resp.setUuid(req.getUuid());
				RecordUtils.writeResponse(logger, resp.getUuid(), resp);
				response.getWriter().write(resp.toJson());
			} else {

				resp = (YiDaoMarkResp) service.responseValue(req);
				resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
				RecordUtils.writeResponse(logger, null, resp);
				response.getWriter().write(resp.toJson());
			}

		} else {// 如果获取参数不成功
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
			RecordUtils.writeResponse(logger, null, resp);
			response.getWriter().write(resp.toJson());
		}
	}

}
