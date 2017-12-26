package com.hengyuan.hicash.parameters.request.notic;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 商户入驻-发送验证码 req
 * 
 * @author Cary.Liu
 * @createDate 2015-07-10
 *
 */
public class SendSupplierCodeReq extends RequestSequence {

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
