package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 手机唯一性验证 请求参数
 * 
 * @author Cary.Liu
 * @create 2014-08-11
 * 
 */
public class OnlyMobileReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
