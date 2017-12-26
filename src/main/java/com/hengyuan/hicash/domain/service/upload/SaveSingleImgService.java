package com.hengyuan.hicash.domain.service.upload;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.apply.TempAppInfoEvent;
import com.hengyuan.hicash.domain.query.app.TempApplyQuery;
import com.hengyuan.hicash.domain.query.user.CustUserQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.PicParam;
import com.hengyuan.hicash.entity.app.TempApplyEntity;
import com.hengyuan.hicash.exception.HttpServletRequestNull;
import com.hengyuan.hicash.exception.PicNotFountException;
import com.hengyuan.hicash.exception.UploadPicException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.upload.SaveSingleImgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.upload.SaveSingleImgResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 图片单张上传 service
 * 嗨秒贷图片上传
 * @author Cary.Liu
 * @createDate 2015-05-26
 *
 */
public class SaveSingleImgService implements RespService {

	private static Logger logger = Logger.getLogger(SaveSingleImgService.class);
	
	private String resultCode = "";
	
	private HttpServletRequest request;
	
	public SaveSingleImgService(HttpServletRequest request){
		this.request = request;
	}
	
	String clickTime = "";
	
	private TempApplyEntity tempApp = null;
	
	private String picUrl = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SaveSingleImgReq imgReq = (SaveSingleImgReq)parmRequest;
		SaveSingleImgResp imgResp = new SaveSingleImgResp();
		TempApplyEntity entity=queryTempByNoName(imgReq.getUserName(),imgReq.getTempAppNo());
		if(entity!=null)
		{
			if(entity.getCreateAppFlg().equals("0")==false)
			{
				imgResp.setResultCode(ResultCodes.STU_APP_APPLY_NOT_OPERO);
				return imgResp;
			}
		}
		try {
			String startTime = DateUtils.getDateStr(new Date());
			Long start = System.currentTimeMillis();
			logger.info("【嗨秒贷=>开始上传图片，时间:"+startTime+"】");
			
			uploadImg(imgReq);
			imgResp.setPicUrl(picUrl);
			String endTime = DateUtils.getDateStr(new Date());
			Long uploadTime = System.currentTimeMillis() - start;
			logger.info("【上嗨秒贷=>传图片完成时间:"+endTime+",耗时：["+uploadTime+"]毫秒】");
			
		} catch (HttpServletRequestNull e) {
			resultCode = ResultCodes.UPLOADPIC_REQUEST_ISNULL;
		} catch (PicNotFountException e){
			resultCode = ResultCodes.UPLOADPIC_APPPIC_NOTFOUND;
		} catch (FileUploadException e) {
			resultCode = ResultCodes.UPLOADPIC_APPPIC_FAIL;
			e.printStackTrace();
		} catch (UploadPicException e) {
			resultCode = ResultCodes.UPLOADPIC_APPPIC_FAIL;
			e.printStackTrace();
		} catch (IOException e) {
			resultCode = ResultCodes.UPLOADPIC_APPPIC_FAIL;
			e.printStackTrace();
		} catch (Exception e) {
			resultCode = ResultCodes.UPLOADPIC_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		imgResp.setResultCode(resultCode);
		return imgResp;
	}
	
	/**
	 * 上传图片
	 * @param imgReq
	 * @throws HttpServletRequestNull 
	 * @throws FileUploadException 
	 * @throws IOException 
	 * @throws FileUploadException 
	 * @throws PicNotFountException 
	 */
	@SuppressWarnings("unchecked")
	public void uploadImg(SaveSingleImgReq imgReq) throws HttpServletRequestNull, FileUploadException, IOException, UploadPicException, PicNotFountException{
		
		logger.info("上传失败eeeeeeeetetet");
		
		//如果request为空或者为错误的请求类型,则抛出异常
		if(request == null || !ServletFileUpload.isMultipartContent(request)){
			throw new HttpServletRequestNull();
		}
		logger.info("上传失败aaaaaaaaaaaaaaaa");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// 允许上传的大小，以字节为单位
		upload.setFileSizeMax(1024 * 1024 * 3);
		List<FileItem> list = upload.parseRequest(request);
		logger.info("上传失败bbbbbbbbbbbbbbbbbb");
		if(list != null && list.size() > 0){

			boolean flag = true;
			
			FileItem fileit = null;
			
			for (FileItem fileItem : list) {
				/* 表单参数 */
				if(fileItem.isFormField()){
					String paramName = fileItem.getFieldName();
					String value = new String(fileItem.getString().getBytes(Consts.SO_ENCODING), Consts.ENCODING);
					
					if(Consts.UPLOAD_USERNAME.equalsIgnoreCase(paramName)){
						imgReq.setUserName(value);
					}else if (Consts.UPLOAD_IMGTYPE.equalsIgnoreCase(paramName)){
						imgReq.setImgType(value);
					}else if (Consts.UPLOAD_TEMPAPPNO.equalsIgnoreCase(paramName)){
						imgReq.setTempAppNo(value);
					}else if ("clickTime".equalsIgnoreCase(paramName)){
						clickTime = value;
					}
					
				}else{
					/* 图片流参数 */
					fileit = fileItem;
				}
			}
			
			/* 参数验证 */
			if(!isUserExist(imgReq.getUserName())){
				resultCode = ResultCodes.UPLOADPIC_USER_NOTFOUND;
				return;
			}
			if(!isTempApplyExist(imgReq.getTempAppNo(), imgReq.getUserName())){
				resultCode = ResultCodes.UPLOADPIC_USERAPPLY_NOTFOUND;
				return;
			}
			
			logger.info("【点击时间："+clickTime+"】");
			if(fileit != null){
				
				byte[] data = getBytesFromFileItem(fileit);
				
				String imgName = imgReq.getImgType();
				
				if (data != null && data.length > 0) {
					
					logger.info("【图片类型:"+imgName+"】");
					
					PicParam picParam = picTypeVal(imgName);
					
					flag = handleUploadPic(imgReq, picParam.getPicName(),picParam.getPicType(), data);
					if(flag){
						resultCode = ResultCodes.NORMAL;
						logger.info("保存图片成功");
					}else{
						logger.info("保存图片失败");
						throw new UploadPicException();
					}
				}
				
			}else{
				logger.info("【图片列表为空】");
				throw new PicNotFountException();
			}
			
		}else{
			logger.info("【参数列表为空】");
			throw new PicNotFountException();
		}
		
	}
	
	/**
	 * 图片处理
	 * @param userName 客户用户名
	 * @param picType 图片类型
	 * @param picName 图片名称
	 * @param data 图片字节数组
	 * @return
	 * @throws UploadPicException 
	 */
	private boolean handleUploadPic(SaveSingleImgReq imgReq,String picType,String picName,byte[] data) throws UploadPicException{
		
		//临时申请件的创建时间(作为图片文件夹名)
		String appCreateTime = tempApp.getCreateDate();
		String timeStamp = DateUtils.getTimeStamp(appCreateTime);
		
		if(appCreateTime == null || "".equals(appCreateTime)){
			logger.info("上传图片失败【"+imgReq.getUserName()+","+tempApp.getTempAppNo()+"该申请件创建时间为空】");
			return false;
		}
		logger.info("上传失败1111111111111");
		// 图片保存路径
		String path = savePicFold(picName, data, tempApp.getTempAppNo(), timeStamp);// 保存图片
		logger.info("path:" + path);
		if (StringUtils.isEmpty(path)) {
			return false;
		}
		logger.info("上传失败22222222222222");
		/* 保存进数据库的路径 */
		String picPath = Consts.PIC_TEMP + timeStamp + Consts.SEPARATOR + tempApp.getTempAppNo() + Consts.SEPARATOR + picName + Consts.PIC_AGO;
		picUrl = ResourceUtils.getValue(Consts.PIC_RESOURCE_URL) + picPath;
		/* 更新图片url */
		
		TempAppInfoEvent tempAppEvent = new TempAppInfoEvent();
		tempAppEvent.updatePicInfo(imgReq, picType, picPath);
		logger.info("上传失败333333333333333333");
		return true;
	}
	
	/**
	 * 保存图片
	 * @param picName
	 * @param data
	 * @return
	 */
	public String savePicFold(String picName, byte[] data, String appNo,String timeStamp) {
		
		String filepath = "";
		try {
			String floderPath = ResourceUtils.getValue(Consts.PIC_UPLOAD_PATH)+ File.separator + Consts.PIC_TEMP + File.separator + timeStamp + File.separator + appNo + File.separator;
//			String floderPath = "http://114.215.194.232:8080/5i5dai-mer"+ File.separator + "app_pic"+ File.separator;
			File file = new File(floderPath);
			if (!file.exists()) {
				file.mkdirs();
			}
			filepath = floderPath + picName + Consts.PIC_AGO;
			FileOutputStream fos;
			fos = new FileOutputStream(filepath);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(data);
			bos.flush();
			bos.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return filepath;
	}
	
	/**
	 * 得到图片byte数组
	 * @param FileItem
	 * @return
	 * @throws IOException
	 */
	private byte[] getBytesFromFileItem(FileItem fileItem)
			throws IOException {
		InputStream is = fileItem.getInputStream();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int ch = -1;
		while ((ch = is.read()) != -1) {
			baos.write(ch);
		}
		return baos.toByteArray();
	}
	
	/**
	 * 验证图片类型
	 * @param picName
	 * @return
	 */
	private PicParam picTypeVal(String imgName){
		
		PicParam param = new PicParam();
		String picName = imgName;
		String picType = "";
		
//		if(imgName.contains(Consts.APP_PIC_TYPE_ZL16)){
//			picType = Consts.APP_PIC_TYPE_ZL16;
//		}else{
			picType = imgName;
//		}
		
		param.setPicName(picName);
		param.setPicType(picType);
		return param;
	}
	
	/**
	 * 用户名是否存在
	 * @param userName
	 * @return
	 */
	public boolean isUserExist(String userName){
		
		CustUserQuery custQuery = new CustUserQuery();
		return custQuery.isUserExist(userName);
	}
	
	public boolean isTempApplyExist(String tempAppNo,String userName){
		
		TempApplyQuery applyQuery = new TempApplyQuery();
		TempApplyEntity entity = applyQuery.queryTempApplyByNo(userName, tempAppNo);
		if(entity != null){
			tempApp = entity;
			return true;
		}
		return false;
	}
	public TempApplyEntity queryTempByNoName(String userName,String tempAppNo){
		
		TempApplyQuery applyQuery = new TempApplyQuery();
		TempApplyEntity entity = applyQuery.queryTempApplyByNo(userName, tempAppNo);
		return entity;
	}
}
