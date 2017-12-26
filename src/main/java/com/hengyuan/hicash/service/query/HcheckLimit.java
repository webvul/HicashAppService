package com.hengyuan.hicash.service.query;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.HinscheckLimitService;
import com.hengyuan.hicash.parameters.request.user.HcheckLimitReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.HinsCheckLimitResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-5-11 下午4:42:26
* 类说明:查询嗨女神能否提额
 */
@WebServlet("/HcheckLimit")
public class HcheckLimit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(HcheckLimit.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		HcheckLimitReq checkLimitReq = new HcheckLimitReq();
		checkLimitReq.setUserName(req.getParameter("username"));
		RecordUtils.writeRequest(logger, req, checkLimitReq);

		String resultCode = validate(checkLimitReq);

		if (!ResultCodes.NORMAL.equals(resultCode)) {
			HinsCheckLimitResp hcheckCreditResp = new HinsCheckLimitResp();
			hcheckCreditResp.setResultCode(resultCode);

			/* 获取返回中文信息 */
			String resuMsg = ResourceUtils.getString(resultCode);
			hcheckCreditResp.setResultMsg(resuMsg);
			resp.getWriter().write(hcheckCreditResp.toJson());

		} else {
			HinscheckLimitService service = new HinscheckLimitService();
			HinsCheckLimitResp checkLimitResp = (HinsCheckLimitResp) service
					.responseValue(checkLimitReq);
//			String resuMsg = ResourceUtils.getString(checkLimitResp
//					.getResultCode());
//			checkLimitResp.setResultMsg(resuMsg);

			ParmResponse parmResponse = checkLimitResp;
			resp.getWriter().write(parmResponse.toJson());

		}

	}

	private String validate(HcheckLimitReq hcheckLimitReq) {

		if (RegexValidate.isNull(hcheckLimitReq.getUserName())) {
			return ResultCodes.USERNAME_NOT_NULL;
		}
		return ResultCodes.NORMAL;
	}

}
