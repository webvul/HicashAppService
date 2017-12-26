package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ValidateCodeValService;
import com.hengyuan.hicash.parameters.request.user.ValidateCodeValReq;
import com.hengyuan.hicash.parameters.response.user.ValidateCodeValResp;
import com.hengyuan.hicash.service.validate.query.ValidateCodeValVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 忘记密码-验证短信验证码
 * @author Cary.Liu
 * @createDate 2015-06-12
 *
 */
@WebServlet("/ValidateCodeVal")
public class ValidateCodeVal extends HttpServlet {

	private static final long serialVersionUID = -167632318633695865L;

	private static Logger logger = Logger.getLogger(ValidateCodeVal.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ValidateCodeValReq valReq = new ValidateCodeValReq();
		valReq.setUuid(req.getParameter("uuid"));
		valReq.setMobileNo(req.getParameter("mobileNo"));
		valReq.setValidateCode(req.getParameter("validateCode"));
		
		RecordUtils.writeRequest(logger, req, valReq);
		ValidateCodeValVal sendVal = new ValidateCodeValVal(valReq); 
		String resultCode = sendVal.validate();
		ValidateCodeValResp valResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			valResp = new ValidateCodeValResp();
			valResp.setResultCode(resultCode);
			valResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ValidateCodeValService valService = new ValidateCodeValService();
			valResp = (ValidateCodeValResp)valService.responseValue(valReq);
			valResp.setResultMsg(ResourceUtils.getString(valResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, valResp);
		resp.getWriter().write(valResp.toJson());
	}
	
}
