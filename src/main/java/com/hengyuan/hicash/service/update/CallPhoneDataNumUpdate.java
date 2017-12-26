package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CallPhoneDataNumUpdateService;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataNumUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneDataNumUpdateResp;
import com.hengyuan.hicash.service.validate.update.CallPhoneDataNumUpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * 电信专案,保存手机号码
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
@WebServlet("/CallPhoneDataNumUpdate")
public class CallPhoneDataNumUpdate extends  HttpServlet {


	private static final long serialVersionUID = 1L;
	
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
		CallPhoneDataNumUpdateReq updateMsgReq = new CallPhoneDataNumUpdateReq();
		updateMsgReq.setUserName(req.getParameter("userName"));
		updateMsgReq.setUuid(req.getParameter("uuid"));
		updateMsgReq.setPhoneDataId(req.getParameter("phoneDataId"));
		updateMsgReq.setPhoneNum(req.getParameter("phoneNum"));
        updateMsgReq.setAppType(req.getParameter("appType"));
        updateMsgReq.setProColor(req.getParameter("proColor"));
		updateMsgReq.setProMerId(req.getParameter("proMerId"));
		updateMsgReq.setProPeriod(req.getParameter("proPeriod"));
		updateMsgReq.setProPrice(req.getParameter("proPrice"));
		updateMsgReq.setAppFlowNo(req.getParameter("appFlowNo"));
		RecordUtils.writeRequest(logger, req, updateMsgReq);
		CallPhoneDataNumUpdateVal infoVal = new CallPhoneDataNumUpdateVal(updateMsgReq);
		String valResult = infoVal.validate();
		if(!ResultCodes.NORMAL.endsWith(valResult)){
			
			CallPhoneDataNumUpdateResp infoResp = new CallPhoneDataNumUpdateResp();
			infoResp.setResultCode(valResult);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(valResult);
			infoResp.setResultMsg(resuMsg);
			
			infoResp.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), infoResp);
			resp.getWriter().write(infoResp.toJson());
			
		}else{
			
			CallPhoneDataNumUpdateService updateMsgService = new CallPhoneDataNumUpdateService();
			
			CallPhoneDataNumUpdateResp  infoResp = (CallPhoneDataNumUpdateResp) updateMsgService.responseValue(updateMsgReq);
			
			String resuMsg = ResourceUtils.getString(infoResp.getResultCode());
			infoResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =infoResp;
			
			parmResponse.setUuid(updateMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, updateMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
			
		}
		
		
	}
}
