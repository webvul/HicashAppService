package com.hengyuan.hicash.parameters.request.amount;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 还款总额（近七天） req
 * 
 * @author Cary.Liu
 * @createDate 2015-10-15
 *
 */
public class RepaymentTotalAmountReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
