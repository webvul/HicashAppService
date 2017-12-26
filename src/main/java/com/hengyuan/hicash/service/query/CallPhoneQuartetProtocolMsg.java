package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CallPhoneQuarteProtocolMsgService;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocolMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocolMsgResp;
import com.hengyuan.hicash.service.validate.query.CallPhoneQuartetProtocolMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 	电信签订四方协议展示，姓名，申请产品名字，每月还款日，每月还款：元期
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
@WebServlet("/CallPhoneQuartetProtocolMsg")
public class CallPhoneQuartetProtocolMsg extends HttpServlet {

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(CallPhoneQuartetProtocolMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CallPhoneQuartetProtocolMsgReq queryMsgReq = new CallPhoneQuartetProtocolMsgReq();
		queryMsgReq.setAppNo(req.getParameter("appNo"));//申请件
	
		queryMsgReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		CallPhoneQuartetProtocolMsgVal val = new CallPhoneQuartetProtocolMsgVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			CallPhoneQuartetProtocolMsgResp callPhoneResp = new CallPhoneQuartetProtocolMsgResp();
			callPhoneResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			callPhoneResp.setResultMsg(resuMsg);
			callPhoneResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), callPhoneResp);
			resp.getWriter().write(callPhoneResp.toJson());
		}else {
			CallPhoneQuarteProtocolMsgService  callPhoneService = new CallPhoneQuarteProtocolMsgService();
			CallPhoneQuartetProtocolMsgResp valresp = (CallPhoneQuartetProtocolMsgResp) callPhoneService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}


}
