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
import com.hengyuan.hicash.domain.service.user.DriverCreditService;
import com.hengyuan.hicash.domain.service.user.JxlOrgApiApplicationsService;
import com.hengyuan.hicash.parameters.request.param.HomePageInfoReq;
import com.hengyuan.hicash.parameters.request.user.JxlOrgApiApplicationsReq;
import com.hengyuan.hicash.parameters.response.param.HomePageInfoResp;
import com.hengyuan.hicash.parameters.response.user.FastLoanFirstMsgResp;
import com.hengyuan.hicash.parameters.response.user.JxlOrgApiApplicationsResp;
import com.hengyuan.hicash.service.validate.query.DriverCreditVal;
import com.hengyuan.hicash.service.validate.query.FastLoanFirstMsgVal;
import com.hengyuan.hicash.service.validate.query.HomePageInfoVal;
import com.hengyuan.hicash.service.validate.query.JxlOrgApiApplicationsVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/** 
 * @author meng.zhang
 * 2017年3月27日 
 * 类说明  司机认证
 */

@WebServlet("/DriverCredit")
public class DriverCredit  extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(DriverCredit.class);
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String  userName = req.getParameter("username");
		String  mobile = req.getParameter("mobile");
		String  time = req.getParameter("time");
		String  password = req.getParameter("password");
		String  message = req.getParameter("message");
		String  seq_no = req.getParameter("seq_no");
		String temp_app_no = req.getParameter("temp_app_no");
		 
		JxlOrgApiApplicationsReq  infoReq = new  JxlOrgApiApplicationsReq();
		JxlOrgApiApplicationsResp infoResp = new JxlOrgApiApplicationsResp();
		 
		infoReq.setMobile(mobile);
		infoReq.setUsername(userName);
		infoReq.setMessage(message);
		infoReq.setSeq_no(seq_no);
		infoReq.setTime(time);
		infoReq.setPassword(password);
		infoReq.setTemp_app_no(temp_app_no);
		
		RecordUtils.writeRequest(logger, req, infoReq);
		
//		infoResp.setResultCode("1");
		DriverCreditVal val = new DriverCreditVal(infoReq);
		
		String restult = val.validate();
		
		if(!ResultCodes.NORMAL.equals(restult)){
			
			infoResp.setResultCode(restult);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(restult);
			infoResp.setResultMsg(resuMsg);
			
		}else{
			
			DriverCreditService driverCredit = new DriverCreditService();
			
			infoResp=(JxlOrgApiApplicationsResp) driverCredit.responseValue(infoReq);
		}
		RecordUtils.writeResponse(logger, null, infoResp);
		resp.getWriter().write(infoResp.toJson());
		
	}
}
