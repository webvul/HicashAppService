package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CmbcIdentifySendCodeService;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifySendCodeReq;
import com.hengyuan.hicash.parameters.response.user.CmbcIdentifySendCodeResp;
import com.hengyuan.hicash.service.validate.update.CmbcIdentifySendCodeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 民生银行代扣银行卡验证CP0032 发送验证码
 * 
 * @author leaf.Ren
 * @createDate 2015-12-01
 * updateDate 2016-08-01
 */
@WebServlet("/CmbcIdentifySendCode")
public class CmbcIdentifySendCode extends HttpServlet {
	private static Logger logger = Logger.getLogger(CmbcIdentifySendCode.class);

	private static final long serialVersionUID = -3754259419900947821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		CmbcIdentifySendCodeReq cmbcSendCodeReq = new CmbcIdentifySendCodeReq();
		cmbcSendCodeReq
				.setUserName(checkNull(request.getParameter("userName")));//用户名
		cmbcSendCodeReq.setAccountNo(checkNull(request
				.getParameter("accountNo")).trim());// 银行账号
		cmbcSendCodeReq.setAccountName(checkNull(request
				.getParameter("accountName")));// 银行账户名称=真实姓名
		
		if(!StringUtils.isEmpty(request.getParameter("certNo"))){
			cmbcSendCodeReq.setCertNo(checkNull(request.getParameter("certNo")).trim().toUpperCase());// 证件号码,代收的时候要大写	
		}else{
			cmbcSendCodeReq.setCertNo("");
		}
		
		cmbcSendCodeReq
				.setMobileNo(checkNull(request.getParameter("mobileNo")).trim());// 手机号码
		cmbcSendCodeReq.setBankCode(checkNull(request.getParameter("bankCode")));//银行编码ABC,CMBC
		
		cmbcSendCodeReq.setAppNo(request.getParameter("appNo"));//申请单号
		
		cmbcSendCodeReq.setDkProvince(request.getParameter("dkProvince"));//开户行省
		
		cmbcSendCodeReq.setDkCity(request.getParameter("dkCity"));//开户行城市
		
		cmbcSendCodeReq.setDkAreaCode(request.getParameter("dkAreaCode"));
		
		cmbcSendCodeReq.setDkBankBranch(request.getParameter("dkBankBranch").trim());//开户支行号
		
		RecordUtils.writeRequest(logger, request, cmbcSendCodeReq);

		CmbcIdentifySendCodeVal sendCodeVal = new CmbcIdentifySendCodeVal(
				cmbcSendCodeReq);
		String resultCode = sendCodeVal.validate();
		CmbcIdentifySendCodeResp sendCodeResp = null;

		if (!ResultCodes.NORMAL.equals(resultCode)) {

			sendCodeResp = new CmbcIdentifySendCodeResp();
			sendCodeResp.setResultCode(resultCode);
			sendCodeResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {

			CmbcIdentifySendCodeService sendCodeService = new CmbcIdentifySendCodeService();
			sendCodeResp = (CmbcIdentifySendCodeResp) sendCodeService
					.responseValue(cmbcSendCodeReq);
			sendCodeResp.setResultMsg(ResourceUtils.getString(sendCodeResp
					.getResultCode()));
		}

		RecordUtils.writeResponse(logger, null, sendCodeResp);
		resp.getWriter().write(sendCodeResp.toJson());
	}

	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
	

}
