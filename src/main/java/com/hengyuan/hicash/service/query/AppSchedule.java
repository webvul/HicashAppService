package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.amount.AppScheduleService;
import com.hengyuan.hicash.parameters.request.amount.AppScheduleReq;
import com.hengyuan.hicash.parameters.response.amount.AppScheduleResp;
import com.hengyuan.hicash.service.validate.query.AppScheduleVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询申请件进度/还款提醒
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
@WebServlet("/AppSchedule")
public class AppSchedule extends HttpServlet {
	
	private static Logger logger = Logger.getLogger(AppSchedule.class);

	private static final long serialVersionUID = 7131378979186499636L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		AppScheduleReq scheduleReq = new AppScheduleReq();
		scheduleReq.setUserName(req.getParameter("userName"));
		scheduleReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, scheduleReq);
		AppScheduleVal scheduleVal = new AppScheduleVal(scheduleReq);
		String resultCode = scheduleVal.validate();
		AppScheduleResp scheduleResp = null;

		if(!ResultCodes.NORMAL.equals(resultCode)){
			
			scheduleResp = new AppScheduleResp();
			scheduleResp.setResultCode(resultCode);
			scheduleResp.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			AppScheduleService scheduleService = new AppScheduleService();
			scheduleResp = (AppScheduleResp)scheduleService.responseValue(scheduleReq);
			scheduleResp.setResultMsg(ResourceUtils.getString(scheduleResp.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, scheduleReq.getUuid(), scheduleResp);
		resp.getWriter().write(scheduleResp.toJson());
	}
	
}
