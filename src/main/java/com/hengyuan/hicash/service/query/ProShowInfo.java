package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.ProShowInfoService;
import com.hengyuan.hicash.parameters.request.param.ProShowInfoReq;
import com.hengyuan.hicash.parameters.response.param.ProShowInfoResp;
import com.hengyuan.hicash.service.validate.query.ProShowInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 产品展示信息
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
@WebServlet("/ProShowInfo")
public class ProShowInfo extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(ProShowInfo.class);

	private static final long serialVersionUID = 2303006395287907990L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ProShowInfoReq proShowReq = new ProShowInfoReq();
		proShowReq.setUuid(req.getParameter("uuid"));
		proShowReq.setCityCode(req.getParameter("cityCode"));
		proShowReq.setChannelId(req.getParameter("channelId"));
//		proShowReq.setPriceSearch(req.getParameter("priceSearch"));
//		proShowReq.setBusinessCircle(req.getParameter("businessCircle"));
		
		RecordUtils.writeRequest(logger, req, proShowReq);
		ProShowInfoVal proShowVal = new ProShowInfoVal(proShowReq);
		String resultCode = proShowVal.validate();
		ProShowInfoResp proShowResp = null;
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			proShowResp = new ProShowInfoResp();
			proShowResp.setResultCode(resultCode);
			proShowResp.setResultMsg(ResourceUtils.getString(resultCode));
			
		}else{
			
			ProShowInfoService proShowService = new ProShowInfoService();
			proShowResp = (ProShowInfoResp)proShowService.responseValue(proShowReq);
			proShowResp.setResultMsg(ResourceUtils.getString(proShowResp.getResultCode()));
		}
		
		proShowResp.setUuid(proShowReq.getUuid());
		RecordUtils.writeResponse(logger, proShowReq.getUuid(), proShowResp);
		resp.getWriter().write(proShowResp.toJson());
	}
	
	
}
