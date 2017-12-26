package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.LoginService;
import com.hengyuan.hicash.parameters.request.user.LoginReq;
import com.hengyuan.hicash.parameters.response.user.LoginResp;
import com.hengyuan.hicash.service.validate.query.LoginVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicashApp用户登录
 * 
 * @author Cary.Liu
 * @createDate 2015-04-20
 *
 */
@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 5576265745019632132L;
	
	private static Logger logger = Logger.getLogger(Login.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		LoginReq loginReq = new LoginReq();
		
		loginReq.setUserName(req.getParameter("userName"));
		loginReq.setPassWord(req.getParameter("passWord"));
		loginReq.setCityCode(req.getParameter("cityCode")); 
		loginReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, loginReq);
		LoginVal loginVal = new LoginVal(loginReq);
		String resultCode = loginVal.validate();
		LoginResp respon = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			/* 请求参数未验证通过 */
			respon = new LoginResp();
			respon.setResultCode(resultCode);
			respon.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			/* 请求参数验证通过，处理业务 */
			LoginService loginService = new LoginService();
			respon = (LoginResp) loginService.responseValue(loginReq);
			respon.setResultMsg(ResourceUtils.getString(respon.getResultCode()));
		}
		
		respon.setUuid(loginReq.getUuid());
		RecordUtils.writeResponse(logger, loginReq.getUuid(), respon);
		resp.getWriter().write(respon.toJson());
	}
	
}
