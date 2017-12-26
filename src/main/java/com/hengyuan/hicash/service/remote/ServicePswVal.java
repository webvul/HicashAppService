package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ServicePswValService;
import com.hengyuan.hicash.parameters.request.user.ServicePswValReq;
import com.hengyuan.hicash.parameters.response.user.ServicePswValResp;
import com.hengyuan.hicash.service.validate.query.ServicePswValVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 验证用户服务密码
 * @author Cary.Liu
 * @createDate 2105-06-09
 *
 */
@WebServlet("/ServicePswVal")
public class ServicePswVal extends HttpServlet {

	private static final long serialVersionUID = 6987243158796383530L;

	private static Logger logger = Logger.getLogger(ServicePswVal.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ServicePswValReq pswReq = new ServicePswValReq();
		pswReq.setUuid(req.getParameter("uuid"));
		pswReq.setUserName(req.getParameter("userName"));
		pswReq.setServicePsw(req.getParameter("servicePsw"));
		pswReq.setAppNo(req.getParameter("appNo"));
		pswReq.setTempAppNo(req.getParameter("tempAppNo"));
		
		pswReq.setDynamicPsw(req.getParameter("dynamicPsw"));
		pswReq.setToken(req.getParameter("token"));
		pswReq.setWebSite(req.getParameter("webSite"));
		pswReq.setSubmitType(req.getParameter("submitType"));
		
		RecordUtils.writeRequest(logger, req, pswReq);
		ServicePswValVal pswVal = new ServicePswValVal(pswReq);
		String resultCode = pswVal.validate();
		ServicePswValResp pswResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			pswResp = new ServicePswValResp();
			pswResp.setResultCode(resultCode);
			pswResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ServicePswValService valService = new ServicePswValService();
			pswResp = (ServicePswValResp)valService.responseValue(pswReq);
			pswResp.setResultMsg(ResourceUtils.getString(pswResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, pswReq.getUuid(), pswResp);
		resp.getWriter().write(pswResp.toJson());
	}
	
}
