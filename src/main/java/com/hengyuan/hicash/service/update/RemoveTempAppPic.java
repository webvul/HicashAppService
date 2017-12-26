package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.upload.RemoveTempAppPicService;
import com.hengyuan.hicash.parameters.request.upload.RemoveTempAppPicReq;
import com.hengyuan.hicash.parameters.response.upload.RemoveTempAppPicResp;
import com.hengyuan.hicash.service.validate.update.RemoveTempAppPicVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 删除临时申请件图片
 * @author Cary.Liu
 * @createDate 2015-12-02
 *
 */
@WebServlet("/RemoveTempAppPic")
public class RemoveTempAppPic extends HttpServlet {

	private static final long serialVersionUID = -8551485579366159134L;
	
	private static Logger logger = Logger.getLogger(RemoveTempAppPic.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RemoveTempAppPicReq picReq = new RemoveTempAppPicReq();
		picReq.setUserName(req.getParameter("userName"));
		picReq.setPicId(req.getParameter("picId"));
		RecordUtils.writeRequest(logger, req, picReq);
		
		RemoveTempAppPicResp picResp = null;
		RemoveTempAppPicVal picVal = new RemoveTempAppPicVal(picReq);
		String resultCode = picVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			picResp = new RemoveTempAppPicResp();
			picResp.setResultCode(resultCode);
			
		}else{
			
			RemoveTempAppPicService picService = new RemoveTempAppPicService();
			picResp = (RemoveTempAppPicResp)picService.responseValue(picReq);
		}
		
		picResp.setResultMsg(ResourceUtils.getString(picResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, picResp);
		resp.getWriter().write(picResp.toJson());
	}
	
}
