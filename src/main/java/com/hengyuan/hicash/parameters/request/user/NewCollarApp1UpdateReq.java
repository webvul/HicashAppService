package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端白领提现申请1完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp1UpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 修改类型 */
	private String updateType;

	/** 申请件流水号 */
	private String appNo;

	/*
	 * 个人信息
	 */
	private String realName;
		
	private String identiyNo;
	
	/** 婚姻状况 */
	private String matrimonySystem;
	
	/** qq */
	private String qqNumber;
	// 家庭电话
	private String homePhoneArea;
	private String homePhoneNum;

	/** 民族 */
	private String nation;

	/** 身份证有效期开始时间 */
	private String idCardValStartDate;

	/** 身份证有效期结束时间 */
	private String idCardValEndDate;

	/** 份证有效期(期限) */
	private String idCardValidity;
	
	/** 借款用途 */
	private String loanUse;
	
	/** 家庭地址-省 */
	private String homeProvince;

	/** 家庭地址-市 */
	private String homeCity;

	/** 家庭地址-区 */
	private String homeDistrict;

	/** 家庭地址-详细 */
	private String homeDetails;
	
	/** 月消费 */
	private String monthlyConsumption;

	public String getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdCardValStartDate() {
		return idCardValStartDate;
	}

	public void setIdCardValStartDate(String idCardValStartDate) {
		this.idCardValStartDate = idCardValStartDate;
	}

	public String getIdCardValEndDate() {
		return idCardValEndDate;
	}

	public void setIdCardValEndDate(String idCardValEndDate) {
		this.idCardValEndDate = idCardValEndDate;
	}

	public String getIdCardValidity() {
		return idCardValidity;
	}

	public void setIdCardValidity(String idCardValidity) {
		this.idCardValidity = idCardValidity;
	}

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
	 * @return the updateType
	 */
	public String getUpdateType() {
		return updateType;
	}

	/**
	 * @param updateType
	 *            the updateType to set
	 */
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}

	/**
	 * @param appNo
	 *            the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentiyNo() {
		return identiyNo;
	}

	public void setIdentiyNo(String identiyNo) {
		this.identiyNo = identiyNo;
	}

	public String getMatrimonySystem() {
		return matrimonySystem;
	}

	public void setMatrimonySystem(String matrimonySystem) {
		this.matrimonySystem = matrimonySystem;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getHomePhoneArea() {
		return homePhoneArea;
	}

	public void setHomePhoneArea(String homePhoneArea) {
		this.homePhoneArea = homePhoneArea;
	}

	public String getHomePhoneNum() {
		return homePhoneNum;
	}

	public void setHomePhoneNum(String homePhoneNum) {
		this.homePhoneNum = homePhoneNum;
	}

	public String getHomeProvince() {
		return homeProvince;
	}

	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	public String getHomeCity() {
		return homeCity;
	}

	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	public String getHomeDistrict() {
		return homeDistrict;
	}

	public void setHomeDistrict(String homeDistrict) {
		this.homeDistrict = homeDistrict;
	}

	public String getHomeDetails() {
		return homeDetails;
	}

	public void setHomeDetails(String homeDetails) {
		this.homeDetails = homeDetails;
	}

	public String getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(String monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	

}
