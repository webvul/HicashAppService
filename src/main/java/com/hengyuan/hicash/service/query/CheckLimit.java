package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CheckLimitService;
import com.hengyuan.hicash.parameters.request.user.CheckLimitReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckCreditResp;
import com.hengyuan.hicash.parameters.response.user.CheckLimitResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询滴滴司机贷客户能否提额
 * 
 * @author teng
 *
 */
@WebServlet("/CheckLimit")
public class CheckLimit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CheckLimit.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CheckLimitReq checkLimitReq = new CheckLimitReq();
		checkLimitReq.setUserName(req.getParameter("userName"));
		RecordUtils.writeRequest(logger, req, checkLimitReq);

		String resultCode = validate(checkLimitReq);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			CheckCreditResp checkCreditResp = new CheckCreditResp();
			checkCreditResp.setResultCode(resultCode);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(resultCode);
			checkCreditResp.setResultMsg(resuMsg);
			resp.getWriter().write(checkCreditResp.toJson());

		} else {
			CheckLimitService service = new CheckLimitService();
			CheckLimitResp checkLimitResp = (CheckLimitResp) service
					.responseValue(checkLimitReq);
//			String resuMsg = ResourceUtils.getString(checkLimitResp
//					.getResultCode());
//			checkLimitResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = checkLimitResp;
			resp.getWriter().write(parmResponse.toJson());

		}

	}

	private String validate(CheckLimitReq checkLimitReq) {

		if (RegexValidate.isNull(checkLimitReq.getUserName())) {
			return ResultCodes.USERNAME_NOT_NULL;
		}
		return ResultCodes.NORMAL;
	}

}
