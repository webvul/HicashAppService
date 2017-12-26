package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.MobileUpdateService;
import com.hengyuan.hicash.parameters.request.user.MobileUpdateReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.MobileUpdateResp;
import com.hengyuan.hicash.service.validate.update.MobileUpdateVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 注册手机修改
 * 
 * @author Eric
 * @create date 2014-07-22
 *
 */
@WebServlet("/MobileUpdate")
public class MobileUpdate extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(MobileUpdate.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		MobileUpdateReq mobileUpdateReq = new MobileUpdateReq();
		
		mobileUpdateReq.setUserName(req.getParameter("userName"));//用户名
		mobileUpdateReq.setOldMobile(req.getParameter("oldMobile"));//原手机号码
		mobileUpdateReq.setNewMobile(req.getParameter("newMobile"));//新手机号码
		mobileUpdateReq.setCertificationCode(req.getParameter("certificationCode"));//验证码
		mobileUpdateReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, mobileUpdateReq);
		//实例化参数验证 
		MobileUpdateVal val = new MobileUpdateVal(mobileUpdateReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			MobileUpdateResp valresp = new MobileUpdateResp();
			valresp.setResultCode(result);
			
			//获取返回中文信息
			String resuMsg = ResourceUtils.getString(result);
			valresp.setResultMsg(resuMsg);
			
			valresp.setUuid(mobileUpdateReq.getUuid());
			
			RecordUtils.writeResponse(logger, mobileUpdateReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}else {
			MobileUpdateService  mobileUpdateService = new MobileUpdateService();
			
			MobileUpdateResp valresp = (MobileUpdateResp) mobileUpdateService.responseValue(mobileUpdateReq);
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =valresp;
			
			parmResponse.setUuid(mobileUpdateReq.getUuid());
			
			RecordUtils.writeResponse(logger, mobileUpdateReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
	}
}
