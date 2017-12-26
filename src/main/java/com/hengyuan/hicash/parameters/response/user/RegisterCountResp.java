package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 注册人数统计
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 * 
 */
public class RegisterCountResp extends ParmResponse {

	/** 返回结果 */
	private String resultCode;

	/** 人数 */
	private String registerCount;

	public String getRegisterCount() {
		return registerCount;
	}

	public void setRegisterCount(String registerCount) {
		this.registerCount = registerCount;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
