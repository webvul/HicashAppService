package com.hengyuan.hicash.service.sms;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SendSupplierCodeService;
import com.hengyuan.hicash.parameters.request.notic.SendSupplierCodeReq;
import com.hengyuan.hicash.parameters.response.notic.SendSupplierCodeResp;
import com.hengyuan.hicash.service.validate.update.SendSupplierCodeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 商户入驻-发送验证码
 * @author Cary.Liu
 * @createDate 2015-07-10
 *
 */
@WebServlet("/SendSupplierCode")
public class SendSupplierCode extends HttpServlet {

	private static final long serialVersionUID = -2387507537216414443L;

	private static Logger logger = Logger.getLogger(SendSupplierCode.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		SendSupplierCodeReq codeReq = new SendSupplierCodeReq();
		codeReq.setMobileNo(req.getParameter("mobileNo"));
		RecordUtils.writeRequest(logger, req, codeReq);
		
		SendSupplierCodeVal codeVal = new SendSupplierCodeVal(codeReq);
		String resultCode = codeVal.validate();
		SendSupplierCodeResp codeResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			codeResp = new SendSupplierCodeResp();
			codeResp.setResultCode(resultCode);
			codeResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			SendSupplierCodeService codeService = new SendSupplierCodeService();
			codeResp = (SendSupplierCodeResp)codeService.responseValue(codeReq);
			codeResp.setResultMsg(ResourceUtils.getString(codeResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, codeResp);
		resp.getWriter().write(codeResp.toJson());
	}
	
}
