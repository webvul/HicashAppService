package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.param.HomePageInfoService;
import com.hengyuan.hicash.parameters.request.param.HomePageInfoReq;
import com.hengyuan.hicash.parameters.response.param.HomePageInfoResp;
import com.hengyuan.hicash.service.validate.query.HomePageInfoVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * app首页展示(图片和热卖商品)
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
@WebServlet("/HomePageInfo")
public class HomePageInfo extends HttpServlet {

	private static final long serialVersionUID = 9055400309332490636L;

	private static final Logger logger = Logger.getLogger(HomePageInfo.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HomePageInfoReq infoReq = new HomePageInfoReq();
		infoReq.setUuid(req.getParameter("uuid"));
		infoReq.setCityCode(req.getParameter("cityCode"));
		infoReq.setUserName(req.getParameter("userName"));
		
		RecordUtils.writeRequest(logger, req, infoReq);
		HomePageInfoVal infoVal = new HomePageInfoVal(infoReq);
		String resultCode = infoVal.validate();
		HomePageInfoResp infoResp = null;
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			infoResp = new HomePageInfoResp();
			infoResp.setResultCode(resultCode);
			infoResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			HomePageInfoService infoService = new HomePageInfoService();
			infoResp = (HomePageInfoResp)infoService.responseValue(infoReq);
			infoResp.setResultMsg(ResourceUtils.getString(infoResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, null, infoResp);
		resp.getWriter().write(infoResp.toJson());
	}
	
}
