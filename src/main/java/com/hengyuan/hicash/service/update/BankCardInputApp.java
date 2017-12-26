package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.domain.service.user.BankCardInputAppService;
import com.hengyuan.hicash.parameters.request.user.BankCardInputAppReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.BankCardInputAppResp;
import com.hengyuan.hicash.service.query.WithHoldingBankCardMsg;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 根据订单号修改绑定的代扣银行卡
 * 
 * @author Lihua.Ren
 * @createDate 2015-12-04
 *
 */
@WebServlet("/BankCardInputApp")
public class BankCardInputApp extends HttpServlet {
	private static final long serialVersionUID = -4030614367830870998L;
	private static Logger logger = Logger
			.getLogger(WithHoldingBankCardMsg.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		BankCardInputAppReq updateApp = new BankCardInputAppReq();
		updateApp.setAppNo(req.getParameter("appNo"));
		updateApp.setBankCard(req.getParameter("bankCard"));
		updateApp.setOpenBank(req.getParameter("openBank"));
		updateApp.setUuid(req.getParameter("uuid"));

		RecordUtils.writeRequest(logger, req, updateApp);

		BankCardInputAppService bankService = new BankCardInputAppService();
		BankCardInputAppResp valresp = (BankCardInputAppResp) bankService
				.responseValue(updateApp);

		String resuMsg = ResourceUtils.getString(valresp.getResultCode());
		valresp.setResultMsg(resuMsg);

		valresp.setUuid(updateApp.getUuid());

		RecordUtils.writeResponse(logger, updateApp.getUuid(), valresp);
		resp.getWriter().write(valresp.toJson());

	}

}
