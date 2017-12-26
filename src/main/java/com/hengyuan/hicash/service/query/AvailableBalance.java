package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.AvailBalanceService;
import com.hengyuan.hicash.parameters.request.user.AvailBalanceReq;
import com.hengyuan.hicash.parameters.response.user.AvailBalanceResp;
import com.hengyuan.hicash.service.validate.query.AvailableBalanceVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
 * 获取账户当前可用余额
 * @author Cary.Liu
 * @createDate 2015-07-03
 * 
 */
@WebServlet("/AvailableBalance")
public class AvailableBalance extends HttpServlet{
	
	private static Logger logger = Logger.getLogger(AvailableBalance.class);
	
	private static final long serialVersionUID = 2L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		/* 实例化获取可用余额接收参数对象 */
		AvailBalanceReq availBalanceReq = new AvailBalanceReq();
		availBalanceReq.setUserName(request.getParameter("userName"));
		availBalanceReq.setUuid(request.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, request, availBalanceReq);
		
		AvailableBalanceVal availableBalanceVal = new AvailableBalanceVal(availBalanceReq);
		String resultCode = availableBalanceVal.validate();
		AvailBalanceResp balanceResp = null;
		
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			balanceResp = new AvailBalanceResp();
			balanceResp.setResultCode(resultCode);
			balanceResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else {
			
			AvailBalanceService availBalanceService = new AvailBalanceService();
			balanceResp = (AvailBalanceResp) availBalanceService.responseValue(availBalanceReq);
			balanceResp.setResultMsg(ResourceUtils.getString(balanceResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, availBalanceReq.getUuid(), balanceResp);
		response.getWriter().write(balanceResp.toJson());
	}
	
	
}
