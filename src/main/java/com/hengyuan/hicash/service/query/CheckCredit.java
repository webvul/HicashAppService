package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.CheckCreditService;
import com.hengyuan.hicash.parameters.request.user.CheckCreditReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CheckCreditResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 查询滴滴司机贷客户能否授信
 * 
 * @author teng
 *
 */
@WebServlet("/CheckCredit")
public class CheckCredit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(CheckCredit.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		CheckCreditReq checkCreditReq = new CheckCreditReq();
		checkCreditReq.setUsername(req.getParameter("username"));
		checkCreditReq.setVersion(req.getParameter("version"));
		RecordUtils.writeRequest(logger, req, checkCreditReq);

		String resultCode = validate(checkCreditReq);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			CheckCreditResp checkCreditResp = new CheckCreditResp();
			checkCreditResp.setResultCode(resultCode);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(resultCode);
			checkCreditResp.setResultMsg(resuMsg);
			resp.getWriter().write(checkCreditResp.toJson());

		} else {
			CheckCreditService service = new CheckCreditService();
			CheckCreditResp checkCreditResp = (CheckCreditResp) service
					.responseValue(checkCreditReq);
//			String resuMsg = ResourceUtils.getString(checkCreditResp
//					.getResultCode());
//			checkCreditResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = checkCreditResp;
			resp.getWriter().write(parmResponse.toJson());

		}

	}

	private String validate(CheckCreditReq checkCreditReq) {
		
		if (RegexValidate.isNull(checkCreditReq.getUsername())) {
			return ResultCodes.USERNAME_NOT_NULL;
		}
		return ResultCodes.NORMAL;
	}

}
