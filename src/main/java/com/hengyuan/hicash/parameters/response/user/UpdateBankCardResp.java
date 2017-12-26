package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 修改银行卡 返回参数
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 * 
 */
public class UpdateBankCardResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
