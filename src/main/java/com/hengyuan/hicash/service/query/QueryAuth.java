package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.QueryAuthService;
import com.hengyuan.hicash.domain.service.user.QuerySingleImgService;
import com.hengyuan.hicash.parameters.request.user.QueryAuthReq;
import com.hengyuan.hicash.parameters.request.user.QuerySingleImgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.QueryAuthResp;
import com.hengyuan.hicash.parameters.response.user.QuerySingleImgResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;


/*
 * 查询授信成功次数和最近一次认证手机号
 * */
@WebServlet("/QueryAuth")
public class QueryAuth extends HttpServlet{
	
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
		
		QueryAuthReq queryAuthReq = new QueryAuthReq();
		queryAuthReq.setUserName(req.getParameter("username"));//用户名
		queryAuthReq.setReserveNumber(req.getParameter("reserveNumber"));
		queryAuthReq.setReservePassword(req.getParameter("reservePassword"));
		queryAuthReq.setIsRequired(req.getParameter("isRequired"));
		RecordUtils.writeRequest(logger, req, queryAuthReq);
		
		String restult = null;
		if (RegexValidate.isNull(queryAuthReq.getUserName())) {
			restult = ResultCodes.USER_ERROR_ISNULL;
		}else{
			restult = ResultCodes.NORMAL;
		}
		if("1".equals(queryAuthReq.getIsRequired())){
			 if(RegexValidate.isNull(queryAuthReq.getReserveNumber())){
				restult = ResultCodes.DDSJ_RESERVER_NUMBER_ISNULL;
			}else if(RegexValidate.isNull(queryAuthReq.getReservePassword())){
				restult = ResultCodes.DDSJ_RESERVER_PASSWORD_ISNULL;
			}
		}
		
		
		if (!ResultCodes.NORMAL.equals(restult)) {
			QueryAuthResp queryAuthResp = new QueryAuthResp();
			queryAuthResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			queryAuthResp.setResultMsg(resuMsg);
			queryAuthResp.setUuid(queryAuthReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryAuthReq.getUuid(), queryAuthResp);
			resp.getWriter().write(queryAuthResp.toJson());
		}else {
			QueryAuthService  queryAuthService = new QueryAuthService();
			QueryAuthResp queryAuthResp = (QueryAuthResp) queryAuthService.responseValue(queryAuthReq);
			
			System.out.println(queryAuthResp.toJson());

			String resuMsg = ResourceUtils.getString(queryAuthResp.getResultCode());
			queryAuthResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = queryAuthResp;
			parmResponse.setUuid(queryAuthReq.getUuid());
			
			RecordUtils.writeResponse(logger, queryAuthReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
	}

}
