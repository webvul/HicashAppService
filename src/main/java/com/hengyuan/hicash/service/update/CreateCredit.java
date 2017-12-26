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
import com.hengyuan.hicash.domain.service.mktApp.CreateCreditService;
import com.hengyuan.hicash.domain.service.mktApp.Singleton;
import com.hengyuan.hicash.parameters.request.mktApp.CreateCreditReq;
import com.hengyuan.hicash.parameters.response.mktApp.CreateCreditResp;
import com.hengyuan.hicash.service.validate.update.CreateCreditVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 司机贷创建正式授信订单
* @author dong.liu 
* 2017-3-23 下午5:59:15
* 类说明
 */
@WebServlet("/CreateCredit")
public class CreateCredit extends HttpServlet {

	private static Logger logger = Logger.getLogger(CreateCredit.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		CreateCreditReq req = new CreateCreditReq();
		
		req.setTemp_no(request.getParameter("temp_no"));
		// end
		RecordUtils.writeRequest(logger, request, req);

		/* 验证表单重复提交 暂无 */
		CreateCreditVal val = new CreateCreditVal(req);
		String resultCode = val.validate();
		CreateCreditResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			resp = new CreateCreditResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			CreateCreditService createAppPayService = new CreateCreditService();
			resp = (CreateCreditResp) createAppPayService
					.responseValue(req);
//			resp = (CreateCreditResp)Singleton.getInstance().method(req);
			resp.setResultMsg(ResourceUtils.getString(resp.getResultCode()));
		}
		RecordUtils.writeResponse(logger, "", resp);
		response.getWriter().write(resp.toJson());
	}
	public static String checkNull(String str) {

		if (StringUtils.isEmpty(str) || "null".equals(str)) {
			return "";
		} else {
			return str;
		}
	}
}
