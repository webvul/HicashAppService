package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.UpdateUserCardService;
import com.hengyuan.hicash.parameters.request.user.UpdateUserCardReq;
import com.hengyuan.hicash.parameters.response.user.UpdateUserCardResp;
import com.hengyuan.hicash.service.validate.update.UpdateUserCardVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 更新用户银行卡以及代扣银行卡
 * 
 * @author jason.wei
 * updateDate 2016-08-24
 */
@WebServlet("/updateUserCard")
public class UpdateUserCard extends HttpServlet {
	private static Logger logger = Logger.getLogger(UpdateUserCard.class);

	private static final long serialVersionUID = -3754259419900947821L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse resp)
			throws ServletException, IOException {

		UpdateUserCardReq updateUserCardReq = new UpdateUserCardReq();
		updateUserCardReq
				.setUserName(checkNull(request.getParameter("userName")));//用户名
		updateUserCardReq.setAccountNo(checkNull(request
				.getParameter("accountNo")));// 银行账号
		updateUserCardReq.setAccountName(checkNull(request
				.getParameter("accountName")));// 银行账户名称=真实姓名
		updateUserCardReq.setBankCode(checkNull(request.getParameter("bankCode")));//银行编码ABC,CMBC
		
		updateUserCardReq.setAppNo(request.getParameter("appNo"));//申请单号
		
		updateUserCardReq.setDkProvince(request.getParameter("dkProvince"));//开户行省
		
		updateUserCardReq.setDkCity(request.getParameter("dkCity"));//开户行城市
		
		updateUserCardReq.setDkAreaCode(request.getParameter("dkAreaCode"));
		
		updateUserCardReq.setDkBankBranch(request.getParameter("dkBankBranch"));//开户支行号
		
		updateUserCardReq.setMobile(checkNull(request.getParameter("mobileNo")).trim());//预留手机号码
		
		RecordUtils.writeRequest(logger, request, updateUserCardReq);

		UpdateUserCardVal updateUserCardVal = new UpdateUserCardVal(
				updateUserCardReq);
		String resultCode = updateUserCardVal.validate();
		UpdateUserCardResp updateUserCardResp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			updateUserCardResp = new UpdateUserCardResp();
			updateUserCardResp.setResultCode(resultCode);
			updateUserCardResp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			UpdateUserCardService updateUserCardService = new UpdateUserCardService();
			updateUserCardResp = (UpdateUserCardResp) updateUserCardService.responseValue(updateUserCardReq);
		}
		RecordUtils.writeResponse(logger, null, updateUserCardResp);
		resp.getWriter().write(updateUserCardResp.toJson());
	}

	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
}
