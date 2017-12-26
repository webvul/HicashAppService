package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UserInfoService;
import com.hengyuan.hicash.parameters.request.user.UserInfoReq;
import com.hengyuan.hicash.parameters.response.user.UserInfoResp;
import com.hengyuan.hicash.service.validate.query.UserInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 是否为蓝领用户
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
@WebServlet("/UserInfo")
public class UserInfo extends HttpServlet {

	private static Logger logger = Logger.getLogger(UserInfo.class);
	
	private static final long serialVersionUID = -8829726452309495109L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserInfoReq userInfoReq = new UserInfoReq();
		userInfoReq.setUserName(req.getParameter("userName"));
		RecordUtils.writeRequest(logger, req, userInfoReq);
		
		UserInfoResp userInfoResp = null;
		UserInfoVal userInfoVal = new UserInfoVal(userInfoReq);
		String resultCode = userInfoVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			userInfoResp = new UserInfoResp();
			userInfoResp.setResultCode(resultCode);
			
		}else{
			
			UserInfoService userInfoService = new UserInfoService();
			userInfoResp = (UserInfoResp)userInfoService.responseValue(userInfoReq);
			
		}
		
		userInfoResp.setResultMsg(ResourceUtils.getString(userInfoResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, userInfoResp);
		resp.getWriter().write(userInfoResp.toJson());
	}
	
}
