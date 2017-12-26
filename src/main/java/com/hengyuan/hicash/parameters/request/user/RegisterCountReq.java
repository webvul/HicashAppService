package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 注册人数统计
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 * 
 */
public class RegisterCountReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 注册类型 */
	private String registerType;

	public String getRegisterType() {
		return registerType;
	}

	public void setRegisterType(String registerType) {
		this.registerType = registerType;
	}

}
