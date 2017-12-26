package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class QueryAuthReq extends RequestSequence{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7164897392758028418L;
	
	/*用户名*/
	private String userName;
	
	/*预留司机账号*/
	private String  reserveNumber;
	
	/* 预留司机密码*/
	private String reservePassword;
	
	private String isRequired; //是否必填
	
	
	
	
	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getReservePassword() {
		return reservePassword;
	}

	public void setReservePassword(String reservePassword) {
		this.reservePassword = reservePassword;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(String reserveNumber) {
		this.reserveNumber = reserveNumber;
	}
	
	
}
