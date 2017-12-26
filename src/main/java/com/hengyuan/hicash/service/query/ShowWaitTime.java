package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.SearchBankCardService;
import com.hengyuan.hicash.domain.service.user.ShowWaitTimeService;
import com.hengyuan.hicash.parameters.request.user.ShowWaitTimeReq;
import com.hengyuan.hicash.parameters.response.user.SearchBankCardResp;
import com.hengyuan.hicash.parameters.response.user.ShowWaitTimeResp;
import com.hengyuan.hicash.service.validate.query.SearchBankCardVal;
import com.hengyuan.hicash.service.validate.query.ShowWaitTimeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * @author blanke.qin
 * 2017年1月9日 下午9:39:33
 * 类说明    获取借款进度
 */
@WebServlet("/ShowWaitTime")
public class ShowWaitTime extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 859512923728805686L;
	private static Logger logger = Logger.getLogger(SearchBankCard.class);
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		ShowWaitTimeResp showWaitTimeResp = new ShowWaitTimeResp();
		ShowWaitTimeReq showWaitTimeReq = new ShowWaitTimeReq();
		
		String app_no = req.getParameter("app_no");
		String authorization = req.getHeader("authorization");
		showWaitTimeReq.setAppNo(app_no);
		showWaitTimeReq.setAuthorization(authorization);
		
		RecordUtils.writeRequest(logger, req, showWaitTimeReq);
		ShowWaitTimeVal waitTime = new ShowWaitTimeVal(showWaitTimeReq);
		String resultCode = waitTime.validate();
		
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			showWaitTimeResp.setResultCode(resultCode);
			showWaitTimeResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {

			ShowWaitTimeService showWaitTimeService = new ShowWaitTimeService();
			showWaitTimeResp = (ShowWaitTimeResp) showWaitTimeService
					.responseValue(showWaitTimeReq);
		}
		
		RecordUtils.writeResponse(logger, showWaitTimeReq.getUuid(),showWaitTimeResp);
		resp.getWriter().write(showWaitTimeResp.toJson());
	}
}
