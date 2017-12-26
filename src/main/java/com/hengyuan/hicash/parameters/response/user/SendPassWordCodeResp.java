package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 用户注册的响应参数
 * 
 * @author Eric
 * @create date 2014-07-24
 * 
 */
public class SendPassWordCodeResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;
	
	/** 用户名 */
	private String userName;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}


	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
