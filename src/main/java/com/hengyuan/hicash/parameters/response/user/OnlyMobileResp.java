package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 手机唯一性验证 返回参数参数
 * 
 * @author Cary.Liu
 * @create 2014-08-11
 * 
 */
public class OnlyMobileResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
