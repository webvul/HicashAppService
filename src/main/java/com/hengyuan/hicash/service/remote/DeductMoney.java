package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.remote.DeductMoneyService;
import com.hengyuan.hicash.parameters.request.user.DeductMoneyReq;
import com.hengyuan.hicash.parameters.response.user.DeductMoneyResp;
import com.hengyuan.hicash.service.validate.update.DeductMoneyVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 扣款
 * @author cary.Liu
 * @createDate 2015-08-27
 *
 */
@WebServlet("/DeductMoney")
public class DeductMoney extends HttpServlet {

	private static final long serialVersionUID = -1783489649004062210L;
	
	private static Logger logger = Logger.getLogger(DeductMoney.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		DeductMoneyReq moneyReq = new DeductMoneyReq();
		moneyReq.setUserName(req.getParameter("userName"));
		moneyReq.setBankCard(req.getParameter("bankCard"));
		moneyReq.setOpenBank(req.getParameter("openBank"));
		moneyReq.setAmount(req.getParameter("amount"));
		RecordUtils.writeRequest(logger, req, moneyReq);
		
		DeductMoneyVal moneyVal = new DeductMoneyVal(moneyReq);
		String resultCode = moneyVal.validate();
		
		DeductMoneyResp moneyResp = null;
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			moneyResp = new DeductMoneyResp();
			moneyResp.setResultCode(resultCode);
			moneyResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			
			DeductMoneyService proxyService = new DeductMoneyService();
			moneyResp = (DeductMoneyResp)proxyService.responseValue(moneyReq);
			moneyResp.setResultMsg(ResourceUtils.getString(moneyResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, moneyResp);
		resp.getWriter().write(moneyResp.toJson());
	}
	
}
