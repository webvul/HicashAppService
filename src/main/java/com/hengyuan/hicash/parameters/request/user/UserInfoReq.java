package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 是否为蓝领用户 req
 * 
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
public class UserInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
