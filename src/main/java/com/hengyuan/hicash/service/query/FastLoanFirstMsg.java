package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.FastLoanFirstService;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.FastLoanFirstMsgResp;
import com.hengyuan.hicash.service.validate.query.FastLoanFirstMsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 秒贷金额期数查询
 * 
 * @author LiHua.Ren
 * @create date 2015-05-26
 */
@WebServlet("/FastLoanFirstMsg")
public class FastLoanFirstMsg extends HttpServlet {
	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(FastLoanFirstMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		FastLoanFirstReq queryMsgReq = new FastLoanFirstReq();
		queryMsgReq.setUserName(req.getParameter("userName"));//用户名
		queryMsgReq.setUuid(req.getParameter("uuid"));
		queryMsgReq.setTempAppNo(req.getParameter("tempAppNo"));
		
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		FastLoanFirstMsgVal val = new FastLoanFirstMsgVal(queryMsgReq);
		String restult = val.validate();
		if (!ResultCodes.NORMAL.equals(restult)) {
			FastLoanFirstMsgResp fastResp = new FastLoanFirstMsgResp();
			fastResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			fastResp.setResultMsg(resuMsg);
			fastResp.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), fastResp);
			resp.getWriter().write(fastResp.toJson());
		}else {
			FastLoanFirstService  stuMsgService = new FastLoanFirstService();
			FastLoanFirstMsgResp valresp = (FastLoanFirstMsgResp) stuMsgService.responseValue(queryMsgReq);

			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
		
	}


}
