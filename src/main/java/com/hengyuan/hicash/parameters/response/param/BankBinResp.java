package com.hengyuan.hicash.parameters.response.param;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * @author fish
 *
 * @date 2017年2月14日 上午10:29:34
 */
public class BankBinResp extends ParmResponse {

	private String bankCode;

	private String bankName;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
