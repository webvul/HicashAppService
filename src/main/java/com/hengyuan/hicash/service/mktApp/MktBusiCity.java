package com.hengyuan.hicash.service.mktApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.mktApp.MktBusiCityService;
import com.hengyuan.hicash.parameters.request.mktApp.MktBusiCityReq;
import com.hengyuan.hicash.parameters.response.mktApp.MktBusiCityResp;
import com.hengyuan.hicash.service.validate.mktApp.MktBusiCityVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 二次营销-业务开放城市
 * @author Cary.Liu
 * @create 2015-03-13
 *
 */
@WebServlet("/MktBusiCity")
public class MktBusiCity extends HttpServlet {
	
	private static final long serialVersionUID = -1477049868967841421L;

	private static Logger logger = Logger.getLogger(MktBusiCity.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		MktBusiCityReq mktReq = new MktBusiCityReq();
		mktReq.setCustType(req.getParameter("custType"));
		mktReq.setUuid(req.getParameter("uuid"));
	
		RecordUtils.writeRequest(logger, req, mktReq);
		
		MktBusiCityVal mktVal = new MktBusiCityVal(mktReq);
		String resultCode = mktVal.validate();
		MktBusiCityResp mktResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			mktResp = new MktBusiCityResp();
			mktResp.setResultCode(resultCode);
			mktResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			
			MktBusiCityService mktService = new MktBusiCityService();
			mktResp = (MktBusiCityResp)mktService.responseValue(mktReq);
			mktResp.setResultMsg(ResourceUtils.getString(mktResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, mktReq.getUuid(), mktResp);
		resp.getWriter().write(mktResp.toJson());
	}
	
}
