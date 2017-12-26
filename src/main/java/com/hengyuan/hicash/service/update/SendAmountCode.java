package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SendAmountCodeService;
import com.hengyuan.hicash.parameters.request.user.SendAmountCodeReq;
import com.hengyuan.hicash.parameters.response.user.SendAmountCodeResp;
import com.hengyuan.hicash.service.validate.update.SendAmountCodeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 获取额度验证码
 * @author Cary.Liu
 * @createDate 2015-04-21
 *
 */
@WebServlet("/SendAmountCode")
public class SendAmountCode extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(SendAmountCode.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		SendAmountCodeReq amountCodeReq = new SendAmountCodeReq();
		amountCodeReq.setUserName(req.getParameter("userName"));
		amountCodeReq.setMobile(req.getParameter("mobile"));
		amountCodeReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, amountCodeReq);
		
		SendAmountCodeVal amountCodeVal = new SendAmountCodeVal(amountCodeReq);
		String resultCode = amountCodeVal.validate();
		SendAmountCodeResp amountCodeResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			amountCodeResp = new SendAmountCodeResp();
			amountCodeResp.setResultCode(resultCode);
			amountCodeResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			SendAmountCodeService amountCodeService = new SendAmountCodeService();
			amountCodeResp = (SendAmountCodeResp) amountCodeService.responseValue(amountCodeReq);
			amountCodeResp.setResultMsg(ResourceUtils.getString(amountCodeResp.getResultCode()));
		}
		
		amountCodeResp.setUuid(amountCodeReq.getUuid());
		RecordUtils.writeResponse(logger, amountCodeReq.getUuid(), amountCodeResp);
		resp.getWriter().write(amountCodeResp.toJson());
	}
}
