package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 修改注册手机号获取验证码响应
 * 
 * @author Eric
 * @create date 2014-07-22
 */
public class MobileUpdateSendCodeResp extends ParmResponse {
	
	/** 返回代码 */
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
}
