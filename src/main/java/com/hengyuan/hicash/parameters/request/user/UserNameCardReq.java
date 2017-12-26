package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;
/**
 * 查询用户真实性别和身份证
 * @author Administrator
 *
 */
public class UserNameCardReq extends RequestSequence{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 用户名 */
	private String userName;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
