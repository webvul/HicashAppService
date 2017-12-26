package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ProxyDeductMoneyNewService;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyNewReq;
import com.hengyuan.hicash.parameters.response.user.ProxyDeductMoneyNewResp;
import com.hengyuan.hicash.service.validate.update.ProxyDeductMoneyNewVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 嗨秒贷
 * 验证用户收款银行卡信息,5块钱
 * 代理扣款
 * @author Lihua.Ren
 * @createDate 2015-11-03
 *
 */
@WebServlet("/ProxyDeductMoneyNew")
public class ProxyDeductMoneyNew extends HttpServlet {

	private static Logger logger = Logger.getLogger(ProxyDeductMoneyNew.class);
	
	private static final long serialVersionUID = -3754259419900947821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ProxyDeductMoneyNewReq  proxyReq = new ProxyDeductMoneyNewReq();
		proxyReq.setUserName(req.getParameter("userName"));
		proxyReq.setBankCard(req.getParameter("bankCard"));
		proxyReq.setOpenBank(req.getParameter("openBank"));
		proxyReq.setMobileNo(req.getParameter("mobileNo"));
//		proxyReq.setAmount(req.getParameter("amount"));
		RecordUtils.writeRequest(logger, req, proxyReq);
		
		ProxyDeductMoneyNewVal proxyVal = new ProxyDeductMoneyNewVal(proxyReq);
		String resultCode = proxyVal.validate();
		ProxyDeductMoneyNewResp proxyResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			proxyResp = new ProxyDeductMoneyNewResp();
			proxyResp.setResultCode(resultCode);
			proxyResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ProxyDeductMoneyNewService proxyService = new ProxyDeductMoneyNewService();
			proxyResp = (ProxyDeductMoneyNewResp)proxyService.responseValue(proxyReq);
//			proxyResp.setResultCode(proxyResp.getResultCode());
			proxyResp.setResultMsg(ResourceUtils.getString(proxyResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, proxyResp);
		resp.getWriter().write(proxyResp.toJson());
	}
	
}
