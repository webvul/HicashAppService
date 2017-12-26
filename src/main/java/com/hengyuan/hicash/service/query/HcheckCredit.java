package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.HinsCheckCreditService;
import com.hengyuan.hicash.parameters.request.user.CheckCreditReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.HcheckCreditResp;
import com.hengyuan.hicash.parameters.response.user.HinscheckCreditResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-5-11 上午9:42:39
* 类说明:查询嗨女神能否授信
 */
@WebServlet("/HcheckCredit")
public class HcheckCredit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(HcheckCredit.class);

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
		RecordUtils.writeRequest(logger, req, checkCreditReq);

		String resultCode = validate(checkCreditReq);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			HcheckCreditResp hcheckCreditResp = new HcheckCreditResp();
			hcheckCreditResp.setResultCode(resultCode);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(resultCode);
			hcheckCreditResp.setResultMsg(resuMsg);
			resp.getWriter().write(hcheckCreditResp.toJson());

		} else {
			HinsCheckCreditService service = new HinsCheckCreditService();
			HinscheckCreditResp hcheckCreditResp = (HinscheckCreditResp) service
					.responseValue(checkCreditReq);
//			String resuMsg = ResourceUtils.getString(checkCreditResp
//					.getResultCode());
//			checkCreditResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = hcheckCreditResp;
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
