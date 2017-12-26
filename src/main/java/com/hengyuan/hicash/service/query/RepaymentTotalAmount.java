package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.amount.RepaymentTotalAmountService;
import com.hengyuan.hicash.parameters.request.amount.RepaymentTotalAmountReq;
import com.hengyuan.hicash.parameters.response.amount.RepaymentTotalAmountResp;
import com.hengyuan.hicash.service.validate.query.RepaymentTotalAmountVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 还款总额（近七天）
 * @author Cary.Liu
 * @createDate 2015-10-15
 *
 */
@WebServlet("/RepaymentTotalAmount")
public class RepaymentTotalAmount extends HttpServlet {

	private static Logger logger = Logger.getLogger(RepaymentTotalAmount.class);
	
	private static final long serialVersionUID = 3075871711813523880L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RepaymentTotalAmountReq totalReq = new RepaymentTotalAmountReq();
		totalReq.setUserName(req.getParameter("userName"));
		totalReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, totalReq);
		RepaymentTotalAmountVal totalVal = new RepaymentTotalAmountVal(totalReq);
		String resultCode = totalVal.validate();
		RepaymentTotalAmountResp totalResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			totalResp = new RepaymentTotalAmountResp();
			totalResp.setResultCode(resultCode);
			totalResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			
			RepaymentTotalAmountService totalService = new RepaymentTotalAmountService();
			totalResp = (RepaymentTotalAmountResp)totalService.responseValue(totalReq);
			totalResp.setResultMsg(ResourceUtils.getString(totalResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, totalReq.getUuid(), totalResp);
		resp.getWriter().write(totalResp.toJson());
	}
	
}
