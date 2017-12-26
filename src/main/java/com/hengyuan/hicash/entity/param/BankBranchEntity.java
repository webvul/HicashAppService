package com.hengyuan.hicash.entity.param;

/**
 * 系统支行
 * @author Cary.Liu
 *
 */
public class BankBranchEntity {

	/** 支行唯一代码 */
	private String bankNum;

	/** 夫银行代码 */
	private String bankNo;

	/** 支行名称 */
	private String bankName;

	/** 支行城市代码(外部公司代码) */
	private String bankCityCode;

	public String getBankNum() {
		return bankNum;
	}

	public void setBankNum(String bankNum) {
		this.bankNum = bankNum;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCityCode() {
		return bankCityCode;
	}

	public void setBankCityCode(String bankCityCode) {
		this.bankCityCode = bankCityCode;
	}

}
