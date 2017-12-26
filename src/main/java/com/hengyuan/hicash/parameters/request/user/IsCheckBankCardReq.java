package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 查询该用户名下的银行卡号是否有校验记录
 * 
 * @author jason
 * @create date 20160804
 */
public class IsCheckBankCardReq extends RequestSequence {

	private static final long serialVersionUID = -5104467559412114306L;
	
	/** 用户名 */
	private String userName;
	

	/** 银行账号 */
	private String accountNo;


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getAccountNo() {
		return accountNo;
	}


	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
