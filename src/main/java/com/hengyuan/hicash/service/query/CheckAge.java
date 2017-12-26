package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CheckAgeService;
import com.hengyuan.hicash.parameters.request.user.CheckAgeReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckAgeResp;
import com.hengyuan.hicash.service.validate.query.CheckAgeVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;


/**
 * 
* @author dong.liu 
* 2017-7-12 下午3:33:20
* 类说明:是否做学信网认证
 */
@WebServlet("/CheckAge")
public class CheckAge extends HttpServlet {


	private static Logger logger = Logger.getLogger(CheckAge.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		CheckAgeReq checkAgeReq = new CheckAgeReq();
		checkAgeReq.setUsername(req.getParameter("username"));

		
		RecordUtils.writeRequest(logger, req, checkAgeReq);
		CheckAgeVal val = new CheckAgeVal(checkAgeReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			CheckAgeResp ageResp = new CheckAgeResp();
			ageResp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			ageResp.setResultMsg(resuMsg);
			RecordUtils.writeResponse(logger, "", ageResp);
			resp.getWriter().write(ageResp.toJson());
		}else {
		
			CheckAgeService checkAgeService = new CheckAgeService();
			
			CheckAgeResp valresp = (CheckAgeResp) checkAgeService.responseValue(checkAgeReq);
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse  = valresp;
			
			RecordUtils.writeResponse(logger, "", parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
		}
}
