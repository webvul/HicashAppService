package com.hengyuan.hicash.service.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SendPswCodeService;
import com.hengyuan.hicash.parameters.request.user.SendPswCodeReq;
import com.hengyuan.hicash.parameters.response.user.SendPswCodeResp;
import com.hengyuan.hicash.service.validate.update.SendPswCodeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 忘记密码-发送验证码
 * @author Cary.Liu
 * @createDate 2015-06-12
 *
 */
@WebServlet("/SendPswCode")
public class SendPswCode extends HttpServlet {

	private static final long serialVersionUID = -6587177566589103463L;
	
	private static Logger logger = Logger.getLogger(SendPswCode.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SendPswCodeReq codeReq = new SendPswCodeReq();
		codeReq.setUuid(req.getParameter("uuid"));
		codeReq.setMobileNo(req.getParameter("mobileNo"));
		
		RecordUtils.writeRequest(logger, req, codeReq);
		SendPswCodeVal codeVal = new SendPswCodeVal(codeReq);
		String resultCode = codeVal.validate();
		SendPswCodeResp codeResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			codeResp = new SendPswCodeResp();
			codeResp.setResultCode(resultCode);
			codeResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			SendPswCodeService codeService = new SendPswCodeService();
			codeResp = (SendPswCodeResp)codeService.responseValue(codeReq);
			codeResp.setResultMsg(ResourceUtils.getString(codeResp.getResultCode()));
		}
		
		RecordUtils.writeRequest(logger, req, codeReq);
		resp.getWriter().write(codeResp.toJson());
	}
	
	
}
