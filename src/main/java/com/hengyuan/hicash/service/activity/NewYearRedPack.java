package com.hengyuan.hicash.service.activity;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.activity.NewYearRedPackService;
import com.hengyuan.hicash.parameters.request.activity.NewYearRedPackReq;
import com.hengyuan.hicash.parameters.response.activity.NewYearRedPackResp;
import com.hengyuan.hicash.service.validate.update.NewYearRedPackVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 新年红包抽取
 * @author Cary.Liu
 * @createDate 2016-02-01
 *
 */
@WebServlet("/NewYearRedPack")
public class NewYearRedPack extends HttpServlet {

	private static final long serialVersionUID = 7414541591132022220L;

	private static Logger logger = Logger.getLogger(NewYearRedPack.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		NewYearRedPackReq redPackReq = new NewYearRedPackReq();
		redPackReq.setUserName(req.getParameter("userName"));
		RecordUtils.writeRequest(logger, req, redPackReq);
		
		NewYearRedPackResp redPackResp = null;
		NewYearRedPackVal redPackVal = new NewYearRedPackVal(redPackReq);
		String resultCode = redPackVal.validate();
		
		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			redPackResp = new NewYearRedPackResp();
			redPackResp.setResultCode(resultCode);
			
		}else{
			
			NewYearRedPackService redPackService = new NewYearRedPackService();
			redPackResp = (NewYearRedPackResp)redPackService.responseValue(redPackReq);
		}
		
		redPackResp.setResultMsg(ResourceUtils.getString(redPackResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, redPackResp);
		resp.getWriter().write(redPackResp.toJson());
	}
	
}
