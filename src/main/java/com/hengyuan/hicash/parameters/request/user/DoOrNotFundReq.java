package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class DoOrNotFundReq extends RequestSequence{

	private String realName;
	private String identityNo;

	private String isOrNot;
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	
	public String getIsOrNot() {
		return isOrNot;
	}
	public void setIsOrNot(String isOrNot) {
		this.isOrNot = isOrNot;
	}
	
	
}
