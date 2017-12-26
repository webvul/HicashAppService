package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CollarApp1MsgService;
import com.hengyuan.hicash.domain.service.user.NewCollarApp3MsgService;
import com.hengyuan.hicash.parameters.request.user.CollarApp1MsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CollarApp1MsgResp;
import com.hengyuan.hicash.parameters.response.user.NewCollarApp3MsgResp;
import com.hengyuan.hicash.service.validate.query.CollarApp1MsgVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 手机端hicash白领资料查询3
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
@WebServlet("/NewCollarApp3Msg")
public class NewCollarApp3Msg extends HttpServlet {

	private static final long serialVersionUID = -8485963175175699076L;
	private static Logger logger = Logger.getLogger(NewCollarApp3Msg.class);	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CollarApp1MsgReq queryMsgReq = new CollarApp1MsgReq();
		
		queryMsgReq.setUserName(req.getParameter("userName"));//用户名
		
		queryMsgReq.setUuid(req.getParameter("uuid"));//uuid
		RecordUtils.writeRequest(logger, req, queryMsgReq);
		CollarApp1MsgVal val = new CollarApp1MsgVal(queryMsgReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			NewCollarApp3MsgResp valresp = new NewCollarApp3MsgResp();
			valresp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			valresp.setResultMsg(resuMsg);
			valresp.setUuid(queryMsgReq.getUuid());
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}else {
		
			NewCollarApp3MsgService  collarQueryService = new NewCollarApp3MsgService();
			
			NewCollarApp3MsgResp valresp = (NewCollarApp3MsgResp) collarQueryService.responseValue(queryMsgReq);
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			parmResponse.setUuid(queryMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		}
}
