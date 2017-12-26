package com.hengyuan.hicash.entity.param;
/**
 * 查询中投支持银行
 * @author leaf.Ren
 * @create 2016-08-01
 *
 */
public class SysCmbcEntity {
	/** 银行编码 */
	private String bankCode;

	/** 银行名 */
	private String bankName;

	/** 是否可用 */
	private String isEnable;

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

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
}
