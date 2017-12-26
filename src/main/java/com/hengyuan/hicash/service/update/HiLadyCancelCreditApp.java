package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.HiLadyCancelCreditAppService;
import com.hengyuan.hicash.parameters.request.mktApp.HilLadyCancelCreditAndLimitReq;
import com.hengyuan.hicash.parameters.response.mktApp.HiLadyCancelCreditAndLimitResp;
import com.hengyuan.hicash.service.validate.update.HiLadyCreditApplyVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-5-12 下午2:27:39
* 类说明 :嗨女神授信订单取消
 */
@WebServlet("/HiLadyCancelCreditApp")
public class HiLadyCancelCreditApp extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger=Logger.getLogger(HiLadyCancelCreditApp.class);
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HilLadyCancelCreditAndLimitReq limitreq= new HilLadyCancelCreditAndLimitReq();
		limitreq.setApp_no(req.getParameter("app_no"));
		RecordUtils.writeRequest(logger, req, limitreq);
		
		HiLadyCreditApplyVal val = new HiLadyCreditApplyVal(limitreq);
		HiLadyCancelCreditAndLimitResp limitresp= null;
		
		String resultCode = val.validate();
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			limitresp = new HiLadyCancelCreditAndLimitResp();
			limitresp.setResultCode(resultCode);
			limitresp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			HiLadyCancelCreditAppService cancelCreditAndLimitService = new HiLadyCancelCreditAppService();
			limitresp = (HiLadyCancelCreditAndLimitResp) cancelCreditAndLimitService.responseValue(limitreq);
			limitresp.setResultMsg(ResourceUtils.getString(limitresp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, limitreq.getUuid(), limitresp);
		resp.getWriter().write(limitresp.toJson());
	}
	

}
