package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 电信签订四方协议展示，姓名，申请产品名字，每月还款日，每月还款：元期----返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPhoneQuartetProtocolMsgResp extends ParmResponse {
	/** 姓名 app_user_realname */
	private String realName;
	/* 产品名字product_name */
	private String appProName;
	/* 还款日 creditDay */
	private String creditDay;

	private String creditName;
	private String userName;

	/** 邮箱 */
	private String email;
	/** 商品价格 */
	private String proAmount;
	/** 期数Id */
	private String loanProductId;
	/** 产品类型 */
	private String proType;
	private String applyAmount;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName
	 *            the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the proType
	 */
	public String getProType() {
		return proType;
	}

	/**
	 * @param proType
	 *            the proType to set
	 */
	public void setProType(String proType) {
		this.proType = proType;
	}

	/**
	 * @return the proAmount
	 */
	public String getProAmount() {
		return proAmount;
	}

	/**
	 * @param proAmount
	 *            the proAmount to set
	 */
	public void setProAmount(String proAmount) {
		this.proAmount = proAmount;
	}

	/**
	 * @return the loanProductId
	 */
	public String getLoanProductId() {
		return loanProductId;
	}

	/**
	 * @param loanProductId
	 *            the loanProductId to set
	 */
	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}

	/**
	 * @param realName
	 *            the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}

	/**
	 * @return the appProName
	 */
	public String getAppProName() {
		return appProName;
	}

	/**
	 * @param appProName
	 *            the appProName to set
	 */
	public void setAppProName(String appProName) {
		this.appProName = appProName;
	}

	/**
	 * @return the creditName
	 */
	public String getCreditName() {
		return creditName;
	}

	/**
	 * @return the creditDay
	 */
	public String getCreditDay() {
		return creditDay;
	}

	/**
	 * @param creditDay
	 *            the creditDay to set
	 */
	public void setCreditDay(String creditDay) {
		this.creditDay = creditDay;
	}

	/**
	 * @param creditName
	 *            the creditName to set
	 */
	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	/**
	 * @return the applyAmount
	 */
	public String getApplyAmount() {
		return applyAmount;
	}

	/**
	 * @param applyAmount
	 *            the applyAmount to set
	 */
	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

}
