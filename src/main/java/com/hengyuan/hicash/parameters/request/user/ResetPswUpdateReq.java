package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 忘记密码-修改密码 req
 * 
 * @author Cary.Liu
 * @createDate 2015-06-12
 * 
 */
public class ResetPswUpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 新密码 */
	private String newPassWord;

	/** 手机号码 */
	private String mobileNo;

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
