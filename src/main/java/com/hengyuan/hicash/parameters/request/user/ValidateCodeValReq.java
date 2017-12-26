package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 忘记密码-短信验证码验证 req
 * 
 * @author Cary.Liu
 * @createDate 2015-06-02
 *
 */
public class ValidateCodeValReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 手机号码 */
	private String mobileNo;

	/** 验证码 */
	private String validateCode;

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

}
