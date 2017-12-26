package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class AccountStatusResp extends ParmResponse {

	private String resultCode;

	/** 是否已授信额度 */
	private String limitFlag;

	/** 是否激活账户 */
	private String accountFlag;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getLimitFlag() {
		return limitFlag;
	}

	public void setLimitFlag(String limitFlag) {
		this.limitFlag = limitFlag;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

}
