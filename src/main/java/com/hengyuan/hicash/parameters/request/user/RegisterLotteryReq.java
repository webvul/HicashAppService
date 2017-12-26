package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 用户注册抽奖 req
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterLotteryReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
