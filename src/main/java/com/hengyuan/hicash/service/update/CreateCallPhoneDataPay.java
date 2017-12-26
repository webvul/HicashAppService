package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.CreateCallPhoneDataService;
import com.hengyuan.hicash.parameters.request.mktApp.CreateAppPayReq;
import com.hengyuan.hicash.parameters.response.mktApp.CreateAppPayResp;
import com.hengyuan.hicash.service.validate.mktApp.CallPhoneDataPayVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 创建申请件
 * 创建电信专案手机申请 
 * @author Cary.Liu
 * @createDate 2015-05-27
 */
@WebServlet("/CreateCallPhoneDataPay")
public class CreateCallPhoneDataPay extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(CreateCallPhoneDataPay.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CreateAppPayReq createAppPayReq = new CreateAppPayReq();

		createAppPayReq.setUuid(request.getParameter("uuid"));
		createAppPayReq.setRebate(request.getParameter("rebate"));
		createAppPayReq.setUserName(request.getParameter("userName"));
		
		createAppPayReq.setMerProId(request.getParameter("merProId"));
		createAppPayReq.setTranPrice(request.getParameter("tranPrice"));
		createAppPayReq.setProColor(request.getParameter("proColor"));
		createAppPayReq.setLoanProduct(request.getParameter("loanProduct"));
		createAppPayReq.setProductName(request.getParameter("productName")+"-"+request.getParameter("proColor"));
		createAppPayReq.setTempAppNo(request.getParameter("tempAppNo"));
		createAppPayReq.setPhoneDataId(request.getParameter("phoneDataId"));
		createAppPayReq.setPhoneNum(request.getParameter("phoneNum"));
		createAppPayReq.setIsDx(request.getParameter("isDx"));
		
		createAppPayReq.setSupplierId(request.getParameter("supplierId"));
		createAppPayReq.setSiteId(request.getParameter("siteId"));
		createAppPayReq.setFirstRate(request.getParameter("firstRate"));
		createAppPayReq.setCustType(request.getParameter("custType"));
		createAppPayReq.setApplyType(request.getParameter("applyType"));
		createAppPayReq.setBankRealName(request.getParameter("bankRealName"));
		createAppPayReq.setOpenBank(request.getParameter("openBank"));
		createAppPayReq.setBankCard(request.getParameter("bankCard"));
		createAppPayReq.setIsSynPerAcct(request.getParameter("isSynPerAcct"));
		createAppPayReq.setCustAppFrom(request.getParameter("custAppFrom"));
		RecordUtils.writeRequest(logger, request, createAppPayReq);
		
		/* 验证表单重复提交 暂无*/
		
		CallPhoneDataPayVal val = new CallPhoneDataPayVal(createAppPayReq);
		String resultCode = val.validate();
		CreateAppPayResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			resp = new CreateAppPayResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		}else {//
			
			CreateCallPhoneDataService createAppPayService = new CreateCallPhoneDataService();
			resp = (CreateAppPayResp) createAppPayService.responseValue(createAppPayReq);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		
		resp.setUuid(createAppPayReq.getUuid());
		RecordUtils.writeResponse(logger, createAppPayReq.getUuid(), resp);
		response.getWriter().write(resp.toJson());
	}

}
