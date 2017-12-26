package com.hengyuan.hicash.domain.service.upload;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.UploadAppPicEvent;
import com.hengyuan.hicash.domain.query.param.TempApplyPicQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.TempApplyPicEntity;
import com.hengyuan.hicash.exception.UploadAppPicException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.upload.UploadAppPicReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UploadAppPicResp;
import com.hengyuan.hicash.utils.DateUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午5:18:07
 */
public class UploadAppPicService implements RespService {

	private HttpServletRequest request;

	public UploadAppPicService(HttpServletRequest request) {
		this.request = request;
	}

	// 上传文件存储目录
	 //private static final String UPLOAD_DIRECTORY = "/ossfile/hmdPic/app_pic/big/";
	 //	private static final String UPLOAD_DIRECTORY = "/hmdPic/app_pic/big/"; // 生产去掉ossfile
	// 缩略图目录
	 //private static final String UPLOAD_DIRECTORY_SMALL = "/ossfile/hmdPic/app_pic/thum/";
	 //private static final String UPLOAD_DIRECTORY_SMALL = "/hmdPic/app_pic/thum/"; // 生产去掉ossfile

	private String bigPath;
	private String smallPath;

	// 上传配置
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		TempApplyPicQuery query = new TempApplyPicQuery();
		UploadAppPicReq req = (UploadAppPicReq) parmRequest;
		UploadAppPicResp resp = new UploadAppPicResp();

		try {
			String fileName = upload(req);
			String result = validate(req, fileName);
			if (result.equals(ResultCodes.NORMAL)) {
				req.setPicName(fileName);
				req.setBigPath(bigPath);
				req.setSmallPath(smallPath);
				// 根据用户名和图片类型查询,如果存在-->更新, 否则新增.
				if (isExist(req)) {
					updatePic(req);
				} else {
					insertPic(req);
				}
			}
			resp.setResultCode(result);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回图片id
		TempApplyPicEntity entity = query.getPicId(req.getUserName(), req.getImgType());
		if (null == entity) {
			entity = new TempApplyPicEntity();
		}
		String picId = entity.getPicId();
		resp.setPicId(picId);
		return resp;
	}

	/**
	 * 新增图片
	 * 
	 * @param req
	 * @throws UploadAppPicException
	 */
	public void insertPic(UploadAppPicReq req) throws UploadAppPicException {
		UploadAppPicEvent event = new UploadAppPicEvent();
		event.insertPic(req);
	}

	/**
	 * 更新图片
	 * 
	 * @param req
	 * @throws UploadAppPicException
	 */
	public void updatePic(UploadAppPicReq req) throws UploadAppPicException {
		UploadAppPicEvent event = new UploadAppPicEvent();
		event.updatePic(req);
	}

	/**
	 * 根据用户名和图片类型判断是否存在.
	 * 
	 * @return
	 */
	public boolean isExist(UploadAppPicReq req) {
		TempApplyPicQuery query = new TempApplyPicQuery();
		int i = query.isExist(req.getUserName(), req.getImgType());
		return i > 0 ? true : false;
	}

	/**
	 * 上传图片
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	public String upload(UploadAppPicReq req) throws IOException {

		// 文件名
		String fileName = "";

		// 配置上传参数
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 设置内存临界值 - 超过后将产生临时文件并存储于临时目录中
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		// 设置临时存储目录
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		ServletFileUpload upload = new ServletFileUpload(factory);

		// 设置最大文件上传值
		upload.setFileSizeMax(MAX_FILE_SIZE);

		// 设置最大请求值 (包含文件和表单数据)
		upload.setSizeMax(MAX_REQUEST_SIZE);

		// 构造临时路径来存储上传的文件
		// 这个路径相对当前应用的目录
		String uploadPath = request.getSession().getServletContext().getRealPath("/") + 
				ResourceUtils.getValue(Consts.PIC_BIG_URL)
				+ DateUtils.getDateStrByFlow2(new Date()) + File.separator + req.getUserName();

		String uploadPath_small = request.getSession().getServletContext().getRealPath("/") + 
				ResourceUtils.getValue(Consts.PIC_THUM_URL)
				+ DateUtils.getDateStrByFlow2(new Date()) + File.separator + req.getUserName();

		// 如果目录不存在则创建
		File big = new File(uploadPath);
		if (!big.exists()) {
			big.mkdirs();
		}

		File small = new File(uploadPath_small);
		if (!small.exists()) {
			small.mkdirs();
		}

		try {
			// 解析请求的内容提取文件数据
			@SuppressWarnings("unchecked")
			List<FileItem> formItems = upload.parseRequest(request);

			if (formItems != null && formItems.size() > 0) {
				// 迭代表单数据
				for (FileItem item : formItems) {
					// 处理不在表单中的字段
					if (!item.isFormField()) {
						fileName = new File(item.getName()).getName();
						String filePath = uploadPath + File.separator + req.getImgType() + Consts.PIC_AGO;
						File storeFile = new File(filePath);
						// 保存文件到硬盘
						item.write(storeFile);
						// 图品缩略图
						lessenPic(uploadPath + File.separator + req.getImgType() + Consts.PIC_AGO, uploadPath_small + File.separator + req.getImgType() + Consts.PIC_AGO,
								90, 90, true);

						bigPath = ResourceUtils.getValue(Consts.PIC_BIG_URL) + DateUtils.getDateStrByFlow2(new Date()) + "/" + req.getUserName()
								+ "/" + req.getImgType() + Consts.PIC_AGO;;
						smallPath = ResourceUtils.getValue(Consts.PIC_THUM_URL) + DateUtils.getDateStrByFlow2(new Date()) + "/"
								+ req.getUserName() + "/" + req.getImgType() + Consts.PIC_AGO;;
					}
				}
			}
		} catch (Exception ex) {
			request.setAttribute("message", "错误信息: " + ex.getMessage());
		}
		return fileName;
	}

	/**
	 * 缩放图像（按高度和宽度缩放）
	 * 
	 * @param resourcePath
	 *            源图像文件地址
	 * @param result
	 *            缩放后的图像地址
	 * @param height
	 *            缩放后的高度
	 * @param width
	 *            缩放后的宽度
	 * @param bb
	 *            比例不对时是否需要补白：true为补白; false为不补白;
	 * @throws Exception
	 */
	public static boolean lessenPic(String resourcePath, String resultPath, int height, int width, boolean bb)
			throws Exception {
		boolean flag = false;
		double ratio = 0.0; // 缩放比例
		File f = new File(resourcePath);
		BufferedImage bi = ImageIO.read(f);
		Image itemp = bi.getScaledInstance(width, height, Image.SCALE_SMOOTH);
		// 计算比例
		if ((bi.getHeight() > height) || (bi.getWidth() > width)) {
			if (bi.getHeight() > bi.getWidth()) {
				ratio = (new Integer(height)).doubleValue() / bi.getHeight();
			} else {
				ratio = (new Integer(width)).doubleValue() / bi.getWidth();
			}
			AffineTransformOp op = new AffineTransformOp(AffineTransform.getScaleInstance(ratio, ratio), null);
			itemp = op.filter(bi, null);
		}
		if (bb) {// 补白
			BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.setColor(Color.white);
			g.fillRect(0, 0, width, height);
			if (width == itemp.getWidth(null))
				g.drawImage(itemp, 0, (height - itemp.getHeight(null)) / 2, itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			else
				g.drawImage(itemp, (width - itemp.getWidth(null)) / 2, 0, itemp.getWidth(null), itemp.getHeight(null),
						Color.white, null);
			g.dispose();
			itemp = image;
		}
		ImageIO.write((BufferedImage) itemp, "jpg", new File(resultPath));
		flag = true;
		return flag;
	}

	public String validate(UploadAppPicReq req, String fileName) {

		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		// 验证上传类型
		if (RegexValidate.isNull(req.getUploadType())) {
			return ResultCodes.UPLOAD_TYPE_ISNULL;
		}

		// 验证图片类型
		if (RegexValidate.isNull(req.getImgType())) {
			return ResultCodes.IMG_TYPE_ISNULL;
		}

		// 验证图片
		if (RegexValidate.isNull(fileName)) {
			return ResultCodes.REGISTER_PICURL_ISNULL;
		}

		return ResultCodes.NORMAL;
	}

}
