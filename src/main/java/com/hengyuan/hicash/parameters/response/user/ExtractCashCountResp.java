package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 提现笔数 返回参数
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 * 
 */
public class ExtractCashCountResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	/** 提现成功笔数 */
	private String succCount;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getSuccCount() {
		return succCount;
	}

	public void setSuccCount(String succCount) {
		this.succCount = succCount;
	}

}
