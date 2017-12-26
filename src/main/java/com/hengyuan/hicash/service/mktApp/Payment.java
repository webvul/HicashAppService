package com.hengyuan.hicash.service.mktApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.PaymentService;
import com.hengyuan.hicash.parameters.request.mktApp.PaymentReq;
import com.hengyuan.hicash.parameters.response.mktApp.PayAmountResp;
import com.hengyuan.hicash.service.validate.mktApp.PaymentVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询首付比率
 * @author Cary.Liu
 * @create 2015-03-12
 *
 */
@WebServlet("/Payment")
public class Payment extends HttpServlet{
	
	private static final long serialVersionUID = 8242569367885885293L;

	private static Logger logger = Logger.getLogger(Payment.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		PaymentReq paymentReq = new PaymentReq();
		paymentReq.setCityCode(req.getParameter("cityCode"));
		paymentReq.setCustType(req.getParameter("custType"));
		
		RecordUtils.writeRequest(logger, req, paymentReq);
		
		PaymentVal paymentVal = new PaymentVal(paymentReq);
		String resultCode = paymentVal.validate();
		PayAmountResp payAmountResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			payAmountResp = new PayAmountResp();
			payAmountResp.setResultCode(resultCode);
			payAmountResp.setUuid(paymentReq.getUuid());
			payAmountResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			PaymentService paymentService = new PaymentService();
			payAmountResp  = (PayAmountResp) paymentService.responseValue(paymentReq);
			payAmountResp.setResultMsg(ResourceUtils.getString(resultCode));
			payAmountResp.setUuid(paymentReq.getUuid());
		}
		
		RecordUtils.writeResponse(logger, paymentReq.getUuid(), payAmountResp);
		resp.getWriter().write(payAmountResp.toJson());
	}
		
}
