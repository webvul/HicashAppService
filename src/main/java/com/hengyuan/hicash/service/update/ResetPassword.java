package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ResetPasswordService;
import com.hengyuan.hicash.parameters.request.user.ResetPasswordReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.ResetPasswordResp;
import com.hengyuan.hicash.service.validate.update.ResetPasswordVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * 忘记密码,更新
 * 
 * @author Eric
 * @create date 2014-07-24
 * 
 */
@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {

	private static final long serialVersionUID = 3949247409856125003L;
	
	private static Logger logger = Logger.getLogger(ResetPassword.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ResetPasswordReq resetPasswordReq = new ResetPasswordReq();

		resetPasswordReq.setUserName(req.getParameter("userName"));

		resetPasswordReq.setNewPassword(req.getParameter("newPassword"));

//		resetPasswordReq.setResetPassword(req.getParameter("resetPassword"));
		
		resetPasswordReq.setUuid(req.getParameter("uuid"));
		
		RecordUtils.writeRequest(logger, req, resetPasswordReq);

		/* 实例化参数验证 */
		ResetPasswordVal val = new ResetPasswordVal(resetPasswordReq);
		String result = val.validate();
		if (!ResultCodes.NORMAL.equals(result)) {
			ResetPasswordResp valresp = new ResetPasswordResp();
			valresp.setResultCode(result);
			/*获取返回中文信息*/
			String resuMsg = ResourceUtils.getString(result);
			valresp.setResultMsg(resuMsg);
			valresp.setUuid(resetPasswordReq.getUuid());
			RecordUtils.writeResponse(logger, resetPasswordReq.getUuid(), valresp);
			resp.getWriter().write(valresp.toJson());
		}else {
			ResetPasswordService service = new ResetPasswordService();

			ResetPasswordResp valresp = (ResetPasswordResp) service.responseValue(resetPasswordReq);
			
			String resuMsg = ResourceUtils.getString(valresp.getResultCode());
			valresp.setResultMsg(resuMsg);
			
			ParmResponse parmResponse =valresp;
			
			parmResponse.setUuid(resetPasswordReq.getUuid());
			RecordUtils.writeResponse(logger, resetPasswordReq.getUuid(), parmResponse);
			resp.getWriter().write(parmResponse.toJson());
		}
	}

}
