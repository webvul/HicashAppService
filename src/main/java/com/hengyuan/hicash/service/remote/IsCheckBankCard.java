package com.hengyuan.hicash.service.remote;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.IsCheckBankCardService;
import com.hengyuan.hicash.parameters.request.user.IsCheckBankCardReq;
import com.hengyuan.hicash.parameters.response.user.IsCheckBankCardResp;
import com.hengyuan.hicash.service.validate.query.IsCheckBankCardVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询该用户名下的银行卡号是否有校验记录
 * 
 * @author jason
 * @createDate 20160804
 */
@WebServlet("/IsCheckBankCard")
public class IsCheckBankCard extends HttpServlet {
	private static Logger logger = Logger.getLogger(IsCheckBankCard.class);

	private static final long serialVersionUID = -3754259419900948921L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		IsCheckBankCardReq isCheckBankCardReq = new IsCheckBankCardReq();
		isCheckBankCardReq.setUserName(checkNull(request.getParameter("userName")));//用户名
		isCheckBankCardReq.setAccountNo(checkNull(request.getParameter("accountNo")));// 银行账号
		
		RecordUtils.writeRequest(logger, request, isCheckBankCardReq);

		IsCheckBankCardVal checkBankCardVal = new IsCheckBankCardVal(isCheckBankCardReq);
		
		String resultCode = checkBankCardVal.validate();
		IsCheckBankCardResp checkBankCardResp = null;

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			checkBankCardResp = new IsCheckBankCardResp();
			checkBankCardResp.setResultCode(resultCode);
			checkBankCardResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			IsCheckBankCardService checkBankCard = new IsCheckBankCardService();
			checkBankCardResp = (IsCheckBankCardResp) checkBankCard.responseValue(isCheckBankCardReq);
			checkBankCardResp.setResultMsg(ResourceUtils.getString(checkBankCardResp.getResultCode()));
		}

		RecordUtils.writeResponse(logger, null, checkBankCardResp);
		resp.getWriter().write(checkBankCardResp.toJson());
	}

	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
}
