package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ResetPswUpdateService;
import com.hengyuan.hicash.parameters.request.user.ResetPswUpdateReq;
import com.hengyuan.hicash.parameters.response.user.ResetPswUpdateResp;
import com.hengyuan.hicash.service.validate.update.ResetPswUpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 忘记密码-修改密码 
 * @author Cary.Liu
 * @createDate 2015-06-12
 * 
 */
@WebServlet("/ResetPswUpdate")
public class ResetPswUpdate extends HttpServlet {

	private static final long serialVersionUID = -7686812498956983997L;

	private static Logger logger = Logger.getLogger(ResetPswUpdate.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ResetPswUpdateReq pswUpdateReq = new ResetPswUpdateReq();
		pswUpdateReq.setUuid(req.getParameter("uuid"));
		pswUpdateReq.setMobileNo(req.getParameter("mobileNo"));
		pswUpdateReq.setNewPassWord(req.getParameter("newPassWord"));
		
		RecordUtils.writeRequest(logger, req, pswUpdateReq);
		
		ResetPswUpdateVal pswUpdateVal = new ResetPswUpdateVal(pswUpdateReq);
		String resultCode = pswUpdateVal.validate();
		ResetPswUpdateResp pswUpdateResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			pswUpdateResp = new ResetPswUpdateResp();
			pswUpdateResp.setResultCode(resultCode);
			pswUpdateResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ResetPswUpdateService pswUpdateService = new ResetPswUpdateService();
			pswUpdateResp = (ResetPswUpdateResp)pswUpdateService.responseValue(pswUpdateReq);
			pswUpdateResp.setResultMsg(ResourceUtils.getString(pswUpdateResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, pswUpdateReq.getUuid(), pswUpdateResp);
		resp.getWriter().write(pswUpdateResp.toJson());
	}
	
}
