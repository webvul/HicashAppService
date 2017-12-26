package com.hengyuan.hicash.parameters.request.amount;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 获取用户还款计划 req
 * 
 * @author Cary.Liu
 * @createDate 2015-05-20
 *
 */
public class UserRepayPlansReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
