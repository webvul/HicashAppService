package com.hengyuan.hicash.service.update.upload;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.upload.UploadAppPicService;
import com.hengyuan.hicash.parameters.request.upload.UploadAppPicReq;
import com.hengyuan.hicash.parameters.response.user.UploadAppPicResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午2:28:38
 */
@WebServlet("/UploadAppPic")
public class UploadAppPic extends HttpServlet {

	private static Logger logger = Logger.getLogger(UploadAppPic.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		UploadAppPicService service = new UploadAppPicService(request);

		UploadAppPicReq req = new UploadAppPicReq();

		req.setUserName(request.getParameter("userName"));
		req.setImgType(request.getParameter("imgType"));
		req.setUploadType(request.getParameter("uploadType"));
		req.setPicName(request.getParameter("picName"));

		RecordUtils.writeRequest(logger, request, req);
		UploadAppPicResp resp = (UploadAppPicResp) service.responseValue(req);
		resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		RecordUtils.writeResponse(logger, null, resp);

		response.getWriter().write(resp.toJson());
	}

}
