package com.hengyuan.hicash.parameters.response.amount;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 用户授信额度响应参数
 * 
 * @author laughing.peng
 * @create date 2014-07-22
 *
 */
public class CreDreamAmtResp extends ParmResponse{
   
	/** 返回代码 */
	private String resultCode;
	
	/** 授信额度 */
	private String amount;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	
	
}
