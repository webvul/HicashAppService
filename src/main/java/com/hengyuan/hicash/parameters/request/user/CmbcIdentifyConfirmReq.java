package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 民生银行代扣业务身份认证-用于验证交易用户验证交易码。CP0030
 * 
 * @author LiHua.Ren
 * @create date 2015-12-02
 */
public class CmbcIdentifyConfirmReq  extends RequestSequence {
	private static final long serialVersionUID = -2385900909581838152L;
	/** 用户名 */
	private String userName; 
	/** 交易流水号 */
	private String bussflowNo; 


	/** 银行编码*/
	private String bankCode; 
	/** 银行账号 */
	private String accountNo;
	/** 银行账户名称 */
	private String accountName; 
	/** 证件号 */
	private String certNo;
	/** 手机号码 */
	private String mobileNo;
	/** 手机验证码*/
	private String phoneVerCode;
	/** 手机验证码令牌 */
	private String phoneToken;
	/**申请单号*/
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
	
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
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
	 * @return the certNo
	 */
	public String getCertNo() {
		return certNo;
	}
	/**
	 * @param certNo the certNo to set
	 */
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}
	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/**
	 * @return the phoneVerCode
	 */
	public String getPhoneVerCode() {
		return phoneVerCode;
	}
	/**
	 * @param phoneVerCode the phoneVerCode to set
	 */
	public void setPhoneVerCode(String phoneVerCode) {
		this.phoneVerCode = phoneVerCode;
	}
	/**
	 * @return the phoneToken
	 */
	public String getPhoneToken() {
		return phoneToken;
	}
	/**
	 * @param phoneToken the phoneToken to set
	 */
	public void setPhoneToken(String phoneToken) {
		this.phoneToken = phoneToken;
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
	
	
}
