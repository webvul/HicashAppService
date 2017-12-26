package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 忘记密码-发送验证码 req
 * 
 * @author Cary.Liu
 * @createDate 2015-06-12
 *
 */
public class SendPswCodeReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 手机号码 */
	private String mobileNo;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

}
