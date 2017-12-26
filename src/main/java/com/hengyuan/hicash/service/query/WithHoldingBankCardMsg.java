package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.WithHoldingBankCardMsgService;
import com.hengyuan.hicash.parameters.request.user.WithHoldingBankCardReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.WithHoldingBankCardResp;
import com.hengyuan.hicash.service.validate.query.WithHoldingBankCardVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
/**
 *查询申请件的代扣银行卡
 * 
 * @author lihua.Ren
 * @create date 2015-12-03
 *
 */
@WebServlet("/WithHoldingBankCardMsg")
public class WithHoldingBankCardMsg extends HttpServlet{
	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(WithHoldingBankCardMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		WithHoldingBankCardReq queryMsgReq = new WithHoldingBankCardReq();
		queryMsgReq.setAppNo(req.getParameter("appNo"));
		queryMsgReq.setUuid(req.getParameter("uuid"));
//		queryMsgReq.setTempAppNo(req.getParameter("tempAppNo"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		WithHoldingBankCardVal val = new WithHoldingBankCardVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			WithHoldingBankCardResp queyResp = new WithHoldingBankCardResp();
			queyResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			queyResp.setResultMsg(resuMsg);
			queyResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), queyResp);
			resp.getWriter().write(queyResp.toJson());
		}else {
			WithHoldingBankCardMsgService  bankService = new WithHoldingBankCardMsgService();
			WithHoldingBankCardResp valresp = (WithHoldingBankCardResp) bankService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);

			valresp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}
		
		
	}

}
