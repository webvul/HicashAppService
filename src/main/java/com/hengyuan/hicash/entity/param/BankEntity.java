package com.hengyuan.hicash.entity.param;

/**
 * 银行实体类
 * 
 * @author Cary.Liu
 * @create 2014-07-28
 * 
 */
public class BankEntity {

	/** 银行编码 */
	private String bankCode;

	/** 银行名 */
	private String bankName;

	/** 唯一代码 */
	private String bankNo;

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

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
