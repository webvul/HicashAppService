package com.hengyuan.hicash.entity.user;

/**
 * 民生银行代扣业务身份认证-用于发送动态验证码到用户手机。CP0032
 * 
 * @author LiHua.Ren
 * @create date 2015-12-01
 */
public class CmbcIdentifySendCodeEntity {
	/** 验证码 */
	private String phoneVerCode;
	/** 令牌信息 */
	private String phoneToken;
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

	/** 发送短信状态 */
	private String valStatus;
	private String createTime;
	private String updateTime;
	/** 交易流水号 验证码*/
	private String bussflowNoConfirm;
	/** 交易流水号 查询*/
	private String bussflowNoQuery;

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
	 * @return the valStatus
	 */
	public String getValStatus() {
		return valStatus;
	}

	/**
	 * @param valStatus the valStatus to set
	 */
	public void setValStatus(String valStatus) {
		this.valStatus = valStatus;
	}

	/**
	 * @return the createTime
	 */
	public String getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
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

	/**
	 * @return the bussflowNoConfirm
	 */
	public String getBussflowNoConfirm() {
		return bussflowNoConfirm;
	}

	/**
	 * @param bussflowNoConfirm the bussflowNoConfirm to set
	 */
	public void setBussflowNoConfirm(String bussflowNoConfirm) {
		this.bussflowNoConfirm = bussflowNoConfirm;
	}

	/**
	 * @return the bussflowNoQuery
	 */
	public String getBussflowNoQuery() {
		return bussflowNoQuery;
	}

	/**
	 * @param bussflowNoQuery the bussflowNoQuery to set
	 */
	public void setBussflowNoQuery(String bussflowNoQuery) {
		this.bussflowNoQuery = bussflowNoQuery;
	}

	


}
