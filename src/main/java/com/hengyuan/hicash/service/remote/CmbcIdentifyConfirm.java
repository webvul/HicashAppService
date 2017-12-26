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
import com.hengyuan.hicash.domain.service.user.CmbcIdentifyConfirmService;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifyConfirmReq;
import com.hengyuan.hicash.parameters.response.user.CmbcIdentifyConfirmResp;
import com.hengyuan.hicash.service.validate.update.CmbcIdentifyConfirmVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 民生银行代扣业务身份认证-用于验证交易用户验证交易码。CP0030
 * 
 * @author leaf.Ren
 * @create date 2015-12-02
 */
@WebServlet("/CmbcIdentifyConfirm")
public class CmbcIdentifyConfirm extends HttpServlet {

	private static Logger logger = Logger.getLogger(CmbcIdentifyConfirm.class);
	
	private static final long serialVersionUID = -3754259419900947821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CmbcIdentifyConfirmReq  confirmReq = new CmbcIdentifyConfirmReq();
		confirmReq.setUserName(req.getParameter("userName"));//用户名
		confirmReq.setAccountNo(checkNull(req.getParameter("accountNo")).trim());//账号
		confirmReq.setAccountName(req.getParameter("accountName"));//账户名称
		confirmReq.setBankCode(req.getParameter("bankCode"));//银行名称
		confirmReq.setPhoneToken(checkNull(req.getParameter("phoneToken")).trim());//手机令牌（按照时间取最新）
		confirmReq.setPhoneVerCode(checkNull(req.getParameter("phoneVerCode")).trim());//手机验证码（优化的时候，可以不保存）
		confirmReq.setMobileNo(checkNull(req.getParameter("mobileNo")).trim());//手机号码
		
		if(!StringUtils.isEmpty(req.getParameter("certNo"))){
			confirmReq.setCertNo(req.getParameter("certNo").trim().toUpperCase());//身份证号// 证件号码,代收的时候要大写	
		}else{
			confirmReq.setCertNo("");//身份证号
		}
		
		confirmReq.setBussflowNo(req.getParameter("bussflowNo")); 
		
		confirmReq.setAppNo(req.getParameter("appNo"));//申请单号
		
		confirmReq.setDkProvince(req.getParameter("dkProvince"));//开户行省
		
		confirmReq.setDkCity(req.getParameter("dkCity"));//开户行城市
		
		confirmReq.setDkAreaCode(req.getParameter("dkAreaCode"));
		
		confirmReq.setDkBankBranch(checkNull(req.getParameter("dkBankBranch")).trim());//开户支行号
		
		
		RecordUtils.writeRequest(logger, req, confirmReq);
		
		CmbcIdentifyConfirmVal confirmVal = new CmbcIdentifyConfirmVal(confirmReq);
		String resultCode = confirmVal.validate();
		CmbcIdentifyConfirmResp confirmResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			confirmResp = new CmbcIdentifyConfirmResp();
			confirmResp.setResultCode(resultCode);
			confirmResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			CmbcIdentifyConfirmService proxyService = new CmbcIdentifyConfirmService();
			confirmResp = (CmbcIdentifyConfirmResp)proxyService.responseValue(confirmReq);

			confirmResp.setResultMsg(ResourceUtils.getString(confirmResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, confirmResp);
		resp.getWriter().write(confirmResp.toJson());
	}
	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
}
