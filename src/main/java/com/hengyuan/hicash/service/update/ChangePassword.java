package com.hengyuan.hicash.service.update;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.service.user.ChangePasswordService;
import com.hengyuan.hicash.parameters.request.user.ChangePasswordReq;
import com.hengyuan.hicash.parameters.response.user.ChangePasswordResp;
import com.hengyuan.hicash.service.validate.update.ChangePasswordVal;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.ResourceUtils;

/**
 * hicash用户修改密码
 * 
 * @author Cary.Liu
 * @createDate 2014-07-15
 *
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ChangePassword.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		/*实例化修改密码的请求参数*/
		ChangePasswordReq changePasswordReq = new ChangePasswordReq();
		changePasswordReq.setUsername(req.getParameter("userName"));
		changePasswordReq.setOldPassword(req.getParameter("oldPassword"));	
		changePasswordReq.setNewPassword(req.getParameter("newPassword"));	
//		changePasswordReq.setConfirmPassword(req.getParameter("confirmPassword"));
		changePasswordReq.setUuid(req.getParameter("uuid"));
		RecordUtils.writeRequest(logger, req, changePasswordReq);
		ChangePasswordVal val = new ChangePasswordVal(changePasswordReq);
		ChangePasswordResp pswRes = null;
		
		String resultCode = val.validate();
		if (!ResultCodes.NORMAL.equals(resultCode)) {
			
			pswRes = new ChangePasswordResp();
			pswRes.setResultCode(resultCode);
			pswRes.setResultMsg(ResourceUtils.getString(resultCode));
		}else{
			
			ChangePasswordService changePasswordService = new ChangePasswordService();
			pswRes = (ChangePasswordResp) changePasswordService.responseValue(changePasswordReq);
			pswRes.setResultMsg(ResourceUtils.getString(pswRes.getResultCode()));
		}
		
		RecordUtils.writeResponse(logger, changePasswordReq.getUuid(), pswRes);
		resp.getWriter().write(pswRes.toJson());
	}
}
