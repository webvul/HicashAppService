package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash用户修改密码请求参数类
 * 
 * @author cary.liu
 * 
 */
public class ChangePasswordReq extends RequestSequence {

	
	private static final long serialVersionUID = 1L;


	/** 用户名 */
	private String username;
	
	/** 原始密码 */
	private String oldPassword;
	
	/** 新密码 */
	private String newPassword;
	
	/** 确认密码 */
	private String confirmPassword;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
