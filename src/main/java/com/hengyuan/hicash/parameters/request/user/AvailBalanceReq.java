package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;


/**
 * 
 * 获取账户当前可用余额 req
 * @author Cary.Liu
 * @createDate 2015-07-03
 * 
 */
public class AvailBalanceReq extends RequestSequence{

	private static final long serialVersionUID = 3L;
	
	/** 用户名 */
	private String userName;


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
	

}
