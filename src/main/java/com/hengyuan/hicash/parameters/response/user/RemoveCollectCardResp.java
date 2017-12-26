package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 删除银行卡 返回参数
 * 
 * @author Cary.Liu
 * @create 2014-08-15
 * 
 */
public class RemoveCollectCardResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

}
