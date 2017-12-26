package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.QueryMyMsgService;
import com.hengyuan.hicash.parameters.request.user.QueryMyMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryAuthResp;
import com.hengyuan.hicash.parameters.response.user.QueryMyMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/*
 * 查询我的消息
 * */
@WebServlet("/QueryMyMsg")
public class QueryMyMsg extends HttpServlet{
	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger.getLogger(QueryAuth.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String maxLine=req.getParameter("maxLine");
		String curPage=req.getParameter("curPage");
		QueryMyMsgReq queryMyMsgReq = new QueryMyMsgReq();
		if(!"".equals(maxLine)&&maxLine!=null){
			queryMyMsgReq.setMaxLine(Integer.parseInt(maxLine));
		}
		if(!"".equals(curPage)&&curPage!=null){
			queryMyMsgReq.setCurPage(curPage);
		}
		queryMyMsgReq.setUserName(req.getParameter("username"));//用户名
		RecordUtils.writeRequest(logger, req, queryMyMsgReq);
		
		String restult = null;
		if (RegexValidate.isNull(queryMyMsgReq.getUserName())) {
			restult = ResultCodes.USER_ERROR_ISNULL;
		}else{
			restult = ResultCodes.NORMAL;
		}
		
		if (!ResultCodes.NORMAL.equals(restult)) {
			QueryMyMsgResp queryMyMsgResp = new QueryMyMsgResp();
			queryMyMsgResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			queryMyMsgResp.setResultMsg(resuMsg);
			queryMyMsgResp.setUuid(queryMyMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMyMsgReq.getUuid(), queryMyMsgResp);
			resp.getWriter().write(queryMyMsgResp.toJson());
		}else {
			QueryMyMsgService  queryMyMsgService = new QueryMyMsgService();
			QueryMyMsgResp queryMyMsgResp = (QueryMyMsgResp) queryMyMsgService.responseValue(queryMyMsgReq);
			
			System.out.println(queryMyMsgResp.toJson());

			String resuMsg = ResourceUtils.getString(queryMyMsgResp.getResultCode());
			queryMyMsgResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = queryMyMsgResp;
			parmResponse.setUuid(queryMyMsgReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryMyMsgReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
	}
}
