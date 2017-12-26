package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ProxyDeductMoneyRechargeService;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyRechargeReq;
import com.hengyuan.hicash.parameters.response.user.ProxyDeductMoneyRechargeResp;
import com.hengyuan.hicash.service.validate.update.ProxyDeductMoneyRechargeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 嗨秒贷
 * 验证用户收款银行卡信息,代扣快捷充值5块钱
 * 代理扣款
 * @author Lihua.Ren
 * @createDate 2015-11-03
 *
 */
@WebServlet("/ProxyDeductMoneyRecharge")
public class ProxyDeductMoneyRecharge extends HttpServlet {

	private static Logger logger = Logger.getLogger(ProxyDeductMoneyRecharge.class);
	
	private static final long serialVersionUID = -3754259419900947821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp); 
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ProxyDeductMoneyRechargeReq  proxyReq = new ProxyDeductMoneyRechargeReq();
		proxyReq.setUserName(req.getParameter("userName"));
		proxyReq.setBankCard(req.getParameter("bankCard"));
		proxyReq.setOpenBank(req.getParameter("openBank"));
		proxyReq.setCustId(req.getParameter("custId"));
		proxyReq.setMerOrderId(req.getParameter("merOrderId"));
		proxyReq.setPhoneToken(req.getParameter("phoneToken"));
		proxyReq.setPhoneVerCode(req.getParameter("phoneVerCode"));
		proxyReq.setMobileNo(req.getParameter("mobileNo"));
		proxyReq.setOrgBussFlowNo(req.getParameter("orgBussFlowNo"));
		proxyReq.setAppNo(req.getParameter("appNo"));
		RecordUtils.writeRequest(logger, req, proxyReq);
		
		ProxyDeductMoneyRechargeVal proxyVal = new ProxyDeductMoneyRechargeVal(proxyReq);
		String resultCode = proxyVal.validate();
		ProxyDeductMoneyRechargeResp proxyResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			proxyResp = new ProxyDeductMoneyRechargeResp();
			proxyResp.setResultCode(resultCode);
			proxyResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ProxyDeductMoneyRechargeService proxyService = new ProxyDeductMoneyRechargeService();
			proxyResp = (ProxyDeductMoneyRechargeResp)proxyService.responseValue(proxyReq);
//			proxyResp.setResultCode(proxyResp.getResultCode());
			proxyResp.setResultMsg(ResourceUtils.getString(proxyResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, proxyResp);
		resp.getWriter().write(proxyResp.toJson());
	}
	
}
