package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.calculate.LoanPayService;
import com.hengyuan.hicash.parameters.request.user.LoanPayReq;
import com.hengyuan.hicash.parameters.request.user.LoanPayResp;
import com.hengyuan.hicash.service.validate.query.LoanPayVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 费率合规计算
 * 
 * @author teng
 *
 */
@WebServlet("/LoanPay")
public class LoanPay extends HttpServlet {
	private static Logger logger = Logger.getLogger(LoanPay.class);
	private static final long serialVersionUID = 3195098942001612859L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		/**
		 * http://localhost:8080/HicashAppService/LoanPay?industryCode=LDDD&amount=800&days=14
		 */
		LoanPayReq loanPayReq = new LoanPayReq();
		LoanPayResp loanPayResp = new LoanPayResp();

		loanPayReq.setIndustryCode(req.getParameter("industryCode"));
		loanPayReq.setAmount(req.getParameter("amount"));
		loanPayReq.setDays(req.getParameter("days"));

		RecordUtils.writeRequest(logger, req, loanPayReq);

		LoanPayVal loanPayVal = new LoanPayVal(loanPayReq);
		String resultCode = loanPayVal.validate();

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			loanPayResp.setResultCode(resultCode);
		} else {

			LoanPayService loanPayService = new LoanPayService();

			loanPayResp = loanPayService.todo(loanPayReq);

		}

		loanPayResp.setResultMsg(ResourceUtils.getString(loanPayResp.getResultCode()));
		RecordUtils.writeResponse(logger, null, loanPayResp);
		resp.getWriter().write(loanPayResp.toJson());

	}

}