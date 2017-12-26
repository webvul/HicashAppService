package com.hengyuan.hicash.parameters.request.user;

public class DoubleIdentityValReq {
	/** 银行编码 */
	private String bankCode; 
	/** 用户名 */
	private String userName;
	/** 交易流水号 */
	private String bussflowNo;

	/** 银行账号 */
	private String accountNo;
	/** 银行账户名称 */
	private String accountName;

	/** 证件号 */
	private String certNo;
	/** 手机号码 */
	private String mobileNo;
	
	/**订单号*/
	private String appNo;
	
	/**代扣方*/
	private String whichPart;
	

	//开户城市-省
	private String dkProvince;
	//开户城市-市
	private String dkCity;
	//开户城市-区
	private String dkAreaCode;
	//开户支行
	private String dkBankBranch;

	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBussflowNo() {
		return bussflowNo;
	}
	public void setBussflowNo(String bussflowNo) {
		this.bussflowNo = bussflowNo;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getWhichPart() {
		return whichPart;
	}
	public void setWhichPart(String whichPart) {
		this.whichPart = whichPart;
	}

	public String getDkProvince() {
		return dkProvince;
	}
	public void setDkProvince(String dkProvince) {
		this.dkProvince = dkProvince;
	}
	public String getDkCity() {
		return dkCity;
	}
	public void setDkCity(String dkCity) {
		this.dkCity = dkCity;
	}
	public String getDkAreaCode() {
		return dkAreaCode;
	}
	public void setDkAreaCode(String dkAreaCode) {
		this.dkAreaCode = dkAreaCode;
	}
	public String getDkBankBranch() {
		return dkBankBranch;
	}
	public void setDkBankBranch(String dkBankBranch) {
		this.dkBankBranch = dkBankBranch;
	}
	
	
}
