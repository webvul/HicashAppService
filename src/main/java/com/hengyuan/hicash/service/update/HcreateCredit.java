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
import com.hengyuan.hicash.domain.service.mktApp.HinsCreateCreditService;
import com.hengyuan.hicash.parameters.request.mktApp.HinsCreateCreditReq;
import com.hengyuan.hicash.parameters.response.mktApp.HinsCreateCreditResp;
import com.hengyuan.hicash.service.validate.update.HinsCreateCreditVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 
* @author dong.liu 
* 2017-5-13 上午11:27:42
* 类说明 :嗨女神创建正式授信订单
 */
@WebServlet("/HcreateCredit")
public class HcreateCredit extends HttpServlet {

	private static Logger logger = Logger.getLogger(HcreateCredit.class);

	private static final long serialVersionUID = -4146078745705753888L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		HinsCreateCreditReq req = new HinsCreateCreditReq();
		
		req.setTemp_no(request.getParameter("temp_no"));
		// end
		RecordUtils.writeRequest(logger, request, req);

		/* 验证表单重复提交 暂无 */
		HinsCreateCreditVal val = new HinsCreateCreditVal(req);
		String resultCode = val.validate();
		HinsCreateCreditResp resp = null;
		if (!ResultCodes.NORMAL.equals(resultCode)) {

			resp = new HinsCreateCreditResp();
			resp.setResultCode(resultCode);
			resp.setResultMsg(ResourceUtils.getString(resultCode));
		} else {
			HinsCreateCreditService createAppPayService = new HinsCreateCreditService();
			resp = (HinsCreateCreditResp) createAppPayService
					.responseValue(req);
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
