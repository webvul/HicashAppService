package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 获取资料接口
 * 忘记密码,更新密码Req
 * @author Eric.shi
 * @create date 2014-07-22
 *
 */

public class ResetPasswordReq extends RequestSequence {

	private static final long serialVersionUID = -6748460336044780619L;
	
	/**用户名*/
	private String userName;
	
	/**新密码*/
	private String newPassword;
	
	/**确认密码*/
	private String resetPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getResetPassword() {
		return resetPassword;
	}

	public void setResetPassword(String resetPassword) {
		this.resetPassword = resetPassword;
	}

}
