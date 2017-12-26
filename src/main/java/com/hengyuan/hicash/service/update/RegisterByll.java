package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RegisterByllService;
import com.hengyuan.hicash.parameters.request.user.RegisterByllReq;
import com.hengyuan.hicash.parameters.response.user.RegisterByllResp;
import com.hengyuan.hicash.service.validate.update.RegisterByllVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 蓝领活动注册
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
@WebServlet("/RegisterByll")
public class RegisterByll extends HttpServlet {

	private static final long serialVersionUID = 963911253492251029L;
	
	private static Logger logger = Logger.getLogger(RegisterByll.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		RegisterByllReq registerReq = new RegisterByllReq();
		registerReq.setCustType(req.getParameter("custType"));
		registerReq.setMobileNo(req.getParameter("mobileNo"));
		registerReq.setIdentifyingCode(req.getParameter("identifyingCode"));
		registerReq.setPassWord(req.getParameter("passWord")); 
		registerReq.setRealName(req.getParameter("realName"));
		registerReq.setIdCard(req.getParameter("idCard")); 
		registerReq.setInviteCode(req.getParameter("inviteCode")); 
		registerReq.setStoreCode(req.getParameter("storeCode")); 
		registerReq.setRegisterFrom(req.getParameter("registerFrom"));
		registerReq.setCustFrom(req.getParameter("custFrom"));
		registerReq.setUserScenepicUrl(req.getParameter("userScenepicUrl"));
		registerReq.setUserScenepicThumUrl(req.getParameter("userScenepicThumUrl"));
		registerReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, registerReq);
		
		RegisterByllResp registerResp = null;
		RegisterByllVal registerVal = new RegisterByllVal(registerReq);
		String resultCode = registerVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			registerResp = new RegisterByllResp();
			registerResp.setResultCode(resultCode);
			
		}else{
			
			RegisterByllService registerService = new RegisterByllService();
			registerResp = (RegisterByllResp)registerService.responseValue(registerReq);
			
		}
		
		registerResp.setResultMsg(ResourceUtils.getString(registerResp.getResultCode()));
		registerResp.setUuid(registerReq.getUuid());
		RecordUtils.writeResponse(logger, registerReq.getUuid(), registerResp);
		resp.getWriter().write(registerResp.toJson());
	}
	
}
