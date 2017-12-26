package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ProxyDeductMoneyService;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyReq;
import com.hengyuan.hicash.parameters.response.user.ProxyDeductMoneyResp;
import com.hengyuan.hicash.service.validate.update.ProxyDeductMoneyVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 嗨秒贷
 * 验证用户收款银行卡信息
 * 代理扣款
 * @author Cary.Liu
 * @createDate 2015-07-22
 *
 */
@WebServlet("/ProxyDeductMoney")
public class ProxyDeductMoney extends HttpServlet {

	private static Logger logger = Logger.getLogger(ProxyDeductMoney.class);
	
	private static final long serialVersionUID = -3754259419900947821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ProxyDeductMoneyReq  proxyReq = new ProxyDeductMoneyReq();
		proxyReq.setUserName(req.getParameter("userName"));
		proxyReq.setBankCard(req.getParameter("bankCard"));
		proxyReq.setOpenBank(req.getParameter("openBank"));
//		proxyReq.setAmount(req.getParameter("amount"));
		
		RecordUtils.writeRequest(logger, req, proxyReq);
		
		ProxyDeductMoneyVal proxyVal = new ProxyDeductMoneyVal(proxyReq);
		String resultCode = proxyVal.validate();
		ProxyDeductMoneyResp proxyResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			proxyResp = new ProxyDeductMoneyResp();
			proxyResp.setResultCode(resultCode);
			proxyResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ProxyDeductMoneyService proxyService = new ProxyDeductMoneyService();
			proxyResp = (ProxyDeductMoneyResp)proxyService.responseValue(proxyReq);
			proxyResp.setResultMsg(ResourceUtils.getString(proxyResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, proxyResp);
		resp.getWriter().write(proxyResp.toJson());
	}
	
}
