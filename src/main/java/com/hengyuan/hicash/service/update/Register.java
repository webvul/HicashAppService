package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RegisterService;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.parameters.response.user.RegisterResp;
import com.hengyuan.hicash.service.validate.update.RegisterVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicashApp用户注册
 * 
 * @author Cary.Liu
 * @createDate 2015-04-21
 *
 */
@WebServlet("/Register")
public class Register extends HttpServlet {

	private static final long serialVersionUID = 1898267091132884143L;
	
	private static Logger logger = Logger.getLogger(Register.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		RegisterReq registerReq = new RegisterReq();
		registerReq.setCustType(req.getParameter("custType"));
		registerReq.setLiveCity(req.getParameter("liveCity"));
		registerReq.setMobileNo(req.getParameter("mobileNo"));
		registerReq.setIdentifyingCode(req.getParameter("identifyingCode"));// 验证码
		registerReq.setPassWord(req.getParameter("passWord")); 
		registerReq.setRealName(req.getParameter("realName"));
		registerReq.setIdCard(req.getParameter("idCard")); 
		registerReq.setUuid(req.getParameter("uuid"));
		
		registerReq.setRegisterFrom(req.getParameter("registerFrom"));
		registerReq.setCustFrom(req.getParameter("custFrom"));
		registerReq.setActiveFrom(req.getParameter("activeFrom"));
		
		RecordUtils.writeRequest(logger, req, registerReq);
		/* 实例化参数验证 */
		RegisterVal registerVal = new RegisterVal(registerReq);
		String resultCode = registerVal.validate();
		RegisterResp registerResp = null;
				
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			registerResp = new RegisterResp();
			registerResp.setResultCode(resultCode);
			registerResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {

			RegisterService registerService = new RegisterService();
			registerResp = (RegisterResp) registerService.responseValue(registerReq);
			registerResp.setResultMsg(ResourceUtils.getString(registerResp.getResultCode()));
		}
		
		registerResp.setUuid(registerReq.getUuid());
		RecordUtils.writeResponse(logger, registerReq.getUuid(), registerResp);
		resp.getWriter().write(registerResp.toJson());
	}
	
}
