package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 修改密码响应
 * 
 * @author Eric
 * @create date 2014-07-24
 * 
 */
public class ResetPasswordResp extends ParmResponse {
	
	/** 返回代码 */
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
