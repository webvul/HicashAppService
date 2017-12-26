package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CallPhoneQuartetProtocolUpService;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocalUpReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocalUpResp;
import com.hengyuan.hicash.service.validate.update.CallPhoneQuarteProtocolUpVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 	电信签订四方协议,修改用户的email
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
@WebServlet("/CallPhoneQuartetProtocolUp")
public class CallPhoneQuartetProtocolUp extends  HttpServlet {
	private static final long serialVersionUID = 6997455447250697777L;
	private static Logger logger = Logger.getLogger(CallPhoneDataNumUpdate.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/* 组装请求参数 */
		CallPhoneQuartetProtocalUpReq updateMsgReq = new CallPhoneQuartetProtocalUpReq();
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUuid(req.getParameter("uuid"));
		updateMsgReq.setEmail(req.getParameter("email"));
		
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		CallPhoneQuarteProtocolUpVal infoVal = new CallPhoneQuarteProtocolUpVal(updateMsgReq);
		String valResult = infoVal.validate();
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			CallPhoneQuartetProtocalUpResp infoResp = new CallPhoneQuartetProtocalUpResp();
			infoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);
			
			infoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
			
		}else{
			
			CallPhoneQuartetProtocolUpService updateMsgService = new CallPhoneQuartetProtocolUpService();
			
			CallPhoneQuartetProtocalUpResp  infoResp = (CallPhoneQuartetProtocalUpResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =infoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
		
	}
}
