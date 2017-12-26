package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 用户登录的请求参数
 * 
 * @author Cary.Liu
 * @create date 2014-07-18
 */
public class LoginUrlReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 登录用户名 */
	private String urlUserName;

	/** 登录密码 */
	private String urlPassword;

	public String getUrlUserName() {
		return urlUserName;
	}

	public void setUrlUserName(String urlUserName) {
		this.urlUserName = urlUserName;
	}

	public String getUrlPassword() {
		return urlPassword;
	}

	public void setUrlPassword(String urlPassword) {
		this.urlPassword = urlPassword;
	}




}
