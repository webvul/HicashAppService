package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CallPhoneNumMsgService;
import com.hengyuan.hicash.parameters.request.user.CallPhoneNumMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneNumMsgResp;
import com.hengyuan.hicash.service.validate.query.CallPhoneNumMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 	根据城市ID,页数，显示条数获取电话号码
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
@WebServlet("/CallPhoneNumMsg")
public class CallPhoneNumMsg extends HttpServlet {

	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(CallPhoneNumMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CallPhoneNumMsgReq queryMsgReq = new CallPhoneNumMsgReq();
		queryMsgReq.setCityCode(req.getParameter("cityCode"));//城市代码
		queryMsgReq.setPageIndex(req.getParameter("pageIndex"));//页数索引
		queryMsgReq.setPageNum(req.getParameter("pageNum"));//显示条数
		queryMsgReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		CallPhoneNumMsgVal val = new CallPhoneNumMsgVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			CallPhoneNumMsgResp callPhoneResp = new CallPhoneNumMsgResp();
			callPhoneResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			callPhoneResp.setResultMsg(resuMsg);
			callPhoneResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), callPhoneResp);
			resp.getWriter().write(callPhoneResp.toJson());
		}else {
			CallPhoneNumMsgService  callPhoneService = new CallPhoneNumMsgService();
			CallPhoneNumMsgResp valresp = (CallPhoneNumMsgResp) callPhoneService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}


}
