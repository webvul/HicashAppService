package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.OnlyMobileService;
import com.hengyuan.hicash.parameters.request.user.OnlyMobileReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.OnlyMobileResp;
import com.hengyuan.hicash.service.validate.query.OnlyMobileVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 手机唯一性验证
 * 
 * @author Cary.Liu
 * @create 2014-08-11
 *
 */
@WebServlet("/OnlyMobile")
public class OnlyMobile extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(OnlyMobile.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		OnlyMobileReq mobileReq = new OnlyMobileReq();
		mobileReq.setMobile(req.getParameter("mobile"));
		mobileReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, mobileReq);
		OnlyMobileVal mobileVal = new OnlyMobileVal(mobileReq);
		String resultCode = mobileVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			OnlyMobileResp mobileResp = new OnlyMobileResp();
			mobileResp.setResultCode(resultCode);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(resultCode);
			mobileResp.setResultMsg(resuMsg);
			RecordUtils.writeResponse(logger, mobileReq.getUuid(), mobileResp);
			resp.getWriter().write(mobileResp.toJson());
			
		}else{
			OnlyMobileService mobileService = new OnlyMobileService();
			
			OnlyMobileResp mobileResp = (OnlyMobileResp) mobileService.responseValue(mobileReq);
			
			String resuMsg = ResourceUtils.getString(mobileResp.getResultCode());
			mobileResp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse = mobileResp;
			parmResponse.setUuid(mobileReq.getUuid());
			
			RecordUtils.writeResponse(logger, mobileReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		
	}
	
}
