package com.hengyuan.hicash.parameters.request.user;

import com.google.gson.Gson;
import com.hengyuan.hicash.parameters.request.RequestSequence;



/**
 * 
* @author blanke.qin
* 2017年4月6日 上午10:00:25
* 类说明
 */
public class RestServerPwdReq extends RequestSequence  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7566864757340483312L;

	/** 用户名 */
	private String mobile;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
