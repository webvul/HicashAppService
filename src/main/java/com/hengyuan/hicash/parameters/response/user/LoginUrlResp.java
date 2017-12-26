package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 用户登录响应参数
 * 
 * @author Mary.Luo
 * @create date 2014-08-11
 */
public class LoginUrlResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;


	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}



}
