package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CallPhoneDataMsgService;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneDataMsgResp;
import com.hengyuan.hicash.parameters.response.user.StuApp1Resp;
import com.hengyuan.hicash.service.validate.query.CallPhoneDataMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 	根据城市ID获取电信套餐内容
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
@WebServlet("/CallPhoneDataMsg")
public class CallPhoneDataMsg extends HttpServlet {

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(CallPhoneDataMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CallPhoneDataMsgReq queryMsgReq = new CallPhoneDataMsgReq();
		queryMsgReq.setCityCode(req.getParameter("cityCode"));//用户名
		queryMsgReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		CallPhoneDataMsgVal val = new CallPhoneDataMsgVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			CallPhoneDataMsgResp callPhoneResp = new CallPhoneDataMsgResp();
			callPhoneResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			callPhoneResp.setResultMsg(resuMsg);
			callPhoneResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), callPhoneResp);
			resp.getWriter().write(callPhoneResp.toJson());
		}else {
			CallPhoneDataMsgService  callPhoneService = new CallPhoneDataMsgService();
			CallPhoneDataMsgResp valresp = (CallPhoneDataMsgResp) callPhoneService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}


}
