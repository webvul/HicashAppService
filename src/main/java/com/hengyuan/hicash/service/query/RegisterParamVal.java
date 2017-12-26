package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RegisterParamValService;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.parameters.response.user.RegisterResp;
import com.hengyuan.hicash.service.validate.update.RegisterParamValVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 用户注册参数验证 servlet
 * @author Cary.Liu
 * @createDate 2015-06-01
 *
 */
@WebServlet("/RegisterParamVal")
public class RegisterParamVal extends HttpServlet {

	private static final long serialVersionUID = 734690487802085740L;

	private static Logger logger = Logger.getLogger(RegisterParamVal.class);
	
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
//		registerReq.setCustType(req.getParameter("custType"));
		registerReq.setLiveCity(req.getParameter("liveCity"));
		registerReq.setMobileNo(req.getParameter("mobileNo"));
		registerReq.setIdentifyingCode(req.getParameter("identifyingCode"));// 验证码
		registerReq.setPassWord(req.getParameter("passWord")); 
		registerReq.setRealName(req.getParameter("realName"));
		registerReq.setIdCard(req.getParameter("idCard")); 
		registerReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, registerReq);
		/* 实例化参数验证 */
		RegisterParamValVal registerVal = new RegisterParamValVal(registerReq);
		String resultCode = registerVal.validate();
		RegisterResp registerResp = null;
				
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			registerResp = new RegisterResp();
			registerResp.setResultCode(resultCode);
			registerResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {

			RegisterParamValService valService = new RegisterParamValService();
			registerResp = (RegisterResp)valService.responseValue(registerReq);
			registerResp.setResultMsg(ResourceUtils.getString(registerResp.getResultCode()));
		}
		
		registerResp.setUuid(registerReq.getUuid());
		RecordUtils.writeResponse(logger, registerReq.getUuid(), registerResp);
		resp.getWriter().write(registerResp.toJson());
	}
	
}
