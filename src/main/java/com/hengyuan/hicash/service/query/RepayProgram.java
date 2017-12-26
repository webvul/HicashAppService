package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.calculate.RepayProgramService;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.calculate.RepayProgramResp;
import com.hengyuan.hicash.parameters.response.user.LoanAmtCalResp;
import com.hengyuan.hicash.service.validate.query.RepayProgramVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;
import com.hengyuan.hicash.utils.StringUtils;

/**
 * 获取VIP贷还款方案
 * @author wangliang
 * @create date 2017/2/22
 */
@WebServlet("/RepayProgram")
public class RepayProgram extends HttpServlet{
	
private static Logger logger = Logger.getLogger(RepayProgram.class);
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LoanAmtCalReq loanAmtCalReq = new LoanAmtCalReq();
		
		loanAmtCalReq.setUserName(request.getParameter("userName"));
		loanAmtCalReq.setCustType(request.getParameter("custType"));
		loanAmtCalReq.setIndustryCode(request.getParameter("industryCode"));
		loanAmtCalReq.setMerProId(request.getParameter("merProId"));
		loanAmtCalReq.setSupplierId(request.getParameter("supplierId"));
		loanAmtCalReq.setSaleSiteId(request.getParameter("saleSiteId"));
		loanAmtCalReq.setUuid(request.getParameter("uuid"));
		
		String verCode = "1";
		
		/* 实例化参数验证 */
		if(!StringUtils.isEmpty(request.getParameter("userName"))){
			RepayProgramVal val = new RepayProgramVal(loanAmtCalReq);
			verCode = val.validate();
		}
		
		if (!ResultCodes.NORMAL.equals(verCode)) {
			LoanAmtCalResp resp = new LoanAmtCalResp();
			resp.setResultCode(verCode);
			
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(verCode);
			resp.setResultMsg(resuMsg);
			resp.setUuid(loanAmtCalReq.getUuid());
			
			RecordUtils.writeResponse(logger, loanAmtCalReq.getUuid(), resp);
			response.getWriter().write(resp.toJson());
		}else {
			
			RepayProgramService repayProgramService = new RepayProgramService();
			
			RepayProgramResp resp = (RepayProgramResp) repayProgramService.responseValue(loanAmtCalReq);
			
			repayProgramService.programListAddHg(resp);
			
			ParmResponse parmResponse = resp;
			
			parmResponse.setUuid(loanAmtCalReq.getUuid());
			
			RecordUtils.writeResponse(logger, loanAmtCalReq.getUuid(), parmResponse);
			response.getWriter().write(parmResponse.toJson());
			
		}
		
	}

}
