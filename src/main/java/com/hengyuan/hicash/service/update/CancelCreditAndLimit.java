package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.CancelCreditAndLimitService;
import com.hengyuan.hicash.parameters.request.mktApp.CancelCreditAndLimitReq;
import com.hengyuan.hicash.parameters.response.mktApp.CancelCreditAndLimitResp;
import com.hengyuan.hicash.service.validate.update.CreditApplyVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
* @author dong.liu 
* 2017-4-7 下午3:16:11
* 类说明:授信订单取消
 */
@WebServlet("/CancelCreditAndLimit")
public class CancelCreditAndLimit extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger=Logger.getLogger(CancelCreditAndLimit.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CancelCreditAndLimitReq limitreq= new CancelCreditAndLimitReq();
		limitreq.setApp_no(req.getParameter("app_no"));
		RecordUtils.writeRequest(logger, req, limitreq);
		
		CreditApplyVal val = new CreditApplyVal(limitreq);
		CancelCreditAndLimitResp limitresp= null;
		
		String resultCode = val.validate();
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			limitresp = new CancelCreditAndLimitResp();
			limitresp.setResultCode(resultCode);
			limitresp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			CancelCreditAndLimitService cancelCreditAndLimitService = new CancelCreditAndLimitService();
			limitresp = (CancelCreditAndLimitResp) cancelCreditAndLimitService.responseValue(limitreq);
			limitresp.setResultMsg(ResourceUtils.getString(limitresp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, limitreq.getUuid(), limitresp);
		resp.getWriter().write(limitresp.toJson());
	}
	

}
