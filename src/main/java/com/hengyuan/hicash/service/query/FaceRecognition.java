package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.user.FaceRecognitionService;
import com.hengyuan.hicash.parameters.request.user.FaceRecognitionReq;
import com.hengyuan.hicash.parameters.response.user.FaceRecognitionResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

@WebServlet("/FaceRecognition")
public class FaceRecognition extends HttpServlet {

	private static Logger logger = Logger.getLogger(FaceRecognition.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		FaceRecognitionService service = new FaceRecognitionService();

		FaceRecognitionReq faceReq = new FaceRecognitionReq();
		faceReq.setIdNo(req.getParameter("idNo"));

		RecordUtils.writeRequest(logger, req, faceReq);
		FaceRecognitionResp faceResp = (FaceRecognitionResp) service.responseValue(faceReq);
		faceResp.setResultMsg(ResourceUtils.getString(faceResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, faceResp);

		resp.getWriter().write(faceResp.toJson());

	}
}
