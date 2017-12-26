package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 	电信签订四方协议,修改用户的email 请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPhoneQuartetProtocalUpReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;
	private String email;

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	

}
