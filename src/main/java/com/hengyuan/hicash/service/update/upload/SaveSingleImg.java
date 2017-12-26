package com.hengyuan.hicash.service.update.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.upload.SaveSingleImgService;
import com.hengyuan.hicash.parameters.request.upload.SaveSingleImgReq;
import com.hengyuan.hicash.parameters.response.upload.SaveSingleImgResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 图片单张上传
 * 嗨秒贷图片上传
 * @author Cary.Liu
 * @createDate 2015-05-26
 *
 */
@WebServlet("/SaveSingleImg")
public class SaveSingleImg extends HttpServlet {

	private static final long serialVersionUID = 5176458728437190121L;
	
	private static Logger logger = Logger.getLogger(SaveSingleImg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
		SaveSingleImgReq imgReq = new SaveSingleImgReq();
		imgReq.setImgType(req.getParameter("imgType"));
		imgReq.setUserName(req.getParameter("userName"));
		imgReq.setTempAppNo(req.getParameter("tempAppNo"));
		imgReq.setClickTime(req.getParameter("clickTime"));
		imgReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, imgReq);
		
		SaveSingleImgService imgService = new SaveSingleImgService(req);
		SaveSingleImgResp imgResp = (SaveSingleImgResp) imgService.responseValue(imgReq);
		imgResp.setResultMsg(ResourceUtils.getString(imgResp.getResultCode()));
		
		RecordUtils.writeResponse(logger, imgReq.getUuid(), imgResp);
		resp.getWriter().write(imgResp.toJson());
	}
	
}
