package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.calculate.RepayProgramNewService;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalNewReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.service.validate.query.RepayProgramNewVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
 * @author teng
 *
 */
@WebServlet("/RepayProgramNew")
public class RepayProgramNew extends HttpServlet {

	private static Logger logger = Logger.getLogger(RepayProgramNew.class);

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		LoanAmtCalNewReq loanAmtCalReq = new LoanAmtCalNewReq();

		loanAmtCalReq.setUserName(request.getParameter("userName"));
		loanAmtCalReq.setIndustryCode(request.getParameter("industryCode"));
		loanAmtCalReq.setApplyCount(request.getParameter("applyCount"));
		loanAmtCalReq.setCustType(request.getParameter("custType"));
		loanAmtCalReq.setLoanAmount(request.getParameter("loanAmount"));
		RepayProgramNewVal val = new RepayProgramNewVal(loanAmtCalReq);
		String restult = val.validate();

		if (!ResultCodes.NORMAL.equals(restult)) {
			LoanAmtCalResp resp = new LoanAmtCalResp();
			resp.setResultCode(restult);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(restult);
			resp.setResultMsg(resuMsg);

			RecordUtils.writeResponse(logger, null, resp);
			response.getWriter().write(resp.toJson());

		} else {

			RepayProgramNewService repayProgramService = new RepayProgramNewService();
			ParmResponse parmResponse = repayProgramService
					.responseValue(loanAmtCalReq);

			RecordUtils.writeResponse(logger, null, parmResponse);
			response.getWriter().write(parmResponse.toJson());

		}

	}

}
