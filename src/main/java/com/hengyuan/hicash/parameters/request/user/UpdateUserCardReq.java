package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 更新用户银行卡
 * 
 * @author jason.wei
 * @create date 20160824
 */
public class UpdateUserCardReq extends RequestSequence {

	private static final long serialVersionUID = -5104467559412114306L;
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

	
	/**订单号*/
	private String appNo;
	
	private String validOk;
	//开户城市-省
	private String dkProvince;
	//开户城市-市
	private String dkCity;
	//开户城市-区
	private String dkAreaCode;
	//开户支行
	private String dkBankBranch;
	
	//预留手机号
	private String mobile;
	
	/**
	 * @return the bussflowNo
	 */
	public String getBussflowNo() {
		return bussflowNo;
	}
	/**
	 * @param bussflowNo the bussflowNo to set
	 */
	public void setBussflowNo(String bussflowNo) {
		this.bussflowNo = bussflowNo;
	}
	/**
	 * @return the accountNo
	 */
	public String getAccountNo() {
		return accountNo;
	}
	/**
	 * @param accountNo the accountNo to set
	 */
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	/**
	 * @return the accountName
	 */
	public String getAccountName() {
		return accountName;
	}
	/**
	 * @param accountName the accountName to set
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getValidOk() {
		return validOk;
	}
	public void setValidOk(String validOk) {
		this.validOk = validOk;
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
