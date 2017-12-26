package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.RegisterCountService;
import com.hengyuan.hicash.parameters.request.user.RegisterCountReq;
import com.hengyuan.hicash.parameters.response.user.RegisterCountResp;
import com.hengyuan.hicash.service.validate.query.RegisterCountVal;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 注册人数统计
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 *
 */
@WebServlet("/RegisterCount")
public class RegisterCount extends HttpServlet {

	private static final long serialVersionUID = 3616035383948211214L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		RegisterCountReq countReq = new RegisterCountReq();
		countReq.setUuid(req.getParameter("uuid"));
		countReq.setRegisterType(req.getParameter("registerType"));
		
		RegisterCountVal countVal = new RegisterCountVal(countReq);
		String resultCode = countVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			RegisterCountResp countResp = new RegisterCountResp();
			countResp.setResultCode(resultCode);
			countResp.setResultMsg(ResourceUtils.getString(resultCode));
			countResp.setUuid(countReq.getUuid());
			
			resp.getWriter().write(countResp.toJson());
		}else{
			
			RegisterCountService countService = new RegisterCountService();
			RegisterCountResp countResp = (RegisterCountResp)countService.responseValue(countReq);
			countResp.setResultMsg(ResourceUtils.getString(countResp.getResultCode()));
			countResp.setUuid(countReq.getUuid());
			
			resp.getWriter().write(countResp.toJson());
		}
		
	}
	
}
