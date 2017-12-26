package com.hengyuan.hicash.parameters.request.param;

/**
 * 银行卡参数
 * @author think
 *
 */
public class CardParam {

	private String userName;

	private String realName;
	
	/** 省份代码 */
	private String province;

	/** 城市代码 */
	private String city;

	private String bank;

	private String bankCard;

	private String bankType;

	private String isDefaultCard;
	
	private String openBankBranch;//开户支行
	
	private String openBankBranchName;//开户支行
	private String area;//开户城市-区
	
	

	public String getOpenBankBranchName() {
		return openBankBranchName;
	}

	public void setOpenBankBranchName(String openBankBranchName) {
		this.openBankBranchName = openBankBranchName;
	}

	public String getOpenBankBranch() {
		return openBankBranch;
	}

	public void setOpenBankBranch(String openBankBranch) {
		this.openBankBranch = openBankBranch;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankType() {
		return bankType;
	}

	public void setBankType(String bankType) {
		this.bankType = bankType;
	}

	public String getIsDefaultCard() {
		return isDefaultCard;
	}

	public void setIsDefaultCard(String isDefaultCard) {
		this.isDefaultCard = isDefaultCard;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

}
