package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class SaveInviteCodeAndNameReq extends RequestSequence {
	
	private static final long serialVersionUID = 1L;

	private String userName;

	/** 邀请码 */
	private String inviteCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}
	
	

}
