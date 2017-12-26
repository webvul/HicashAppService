package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端白领提现申请1查询返回信息
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp1MsgResp extends ParmResponse {
	
	/** 客户类型 */
	private String custType;
	/** 客户类型名称 */
	private String custTypeName;
	
    private String realName;
	
	private String identiyNo;
	
	/** 婚姻状况 */
	private String maritalStatus;
	
	/** 婚姻状况名称 */
	private String maritalStatusName;

	/** 家庭地址--省 */
	private String fimilyPro;

	private String fimilyProName;

	/** 家庭地址--市 */
	private String fimilyCity;

	private String fimilyCityName;

	/** 家庭地址--区 */
	private String fimilyArea;

	private String fimilyAreaName;

	/** 家庭街道地址 */
	private String fimilyRoad;
	/** 邮寄地址类型 */
	private String expressType;
	/** 邮寄地址名称 */
	private String expressTypeName;

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
	 * @return the qqNumber
	 */
	public String getQqNumber() {
		return qqNumber;
	}

	/**
	 * @param qqNumber
	 *            the qqNumber to set
	 */
	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	/**
	 * @return the homePhoneArea
	 */
	public String getHomePhoneArea() {
		return homePhoneArea;
	}

	/**
	 * @param homePhoneArea
	 *            the homePhoneArea to set
	 */
	public void setHomePhoneArea(String homePhoneArea) {
		this.homePhoneArea = homePhoneArea;
	}

	/**
	 * @return the homePhoneNum
	 */
	public String getHomePhoneNum() {
		return homePhoneNum;
	}

	/**
	 * @param homePhoneNum
	 *            the homePhoneNum to set
	 */
	public void setHomePhoneNum(String homePhoneNum) {
		this.homePhoneNum = homePhoneNum;
	}

	/**
	 * @return the maritalStatus
	 */
	public String getMaritalStatus() {
		return maritalStatus;
	}

	/**
	 * @param maritalStatus
	 *            the maritalStatus to set
	 */
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustTypeName() {
		return custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
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

	public String getFimilyPro() {
		return fimilyPro;
	}

	public void setFimilyPro(String fimilyPro) {
		this.fimilyPro = fimilyPro;
	}

	public String getFimilyProName() {
		return fimilyProName;
	}

	public void setFimilyProName(String fimilyProName) {
		this.fimilyProName = fimilyProName;
	}

	public String getFimilyCity() {
		return fimilyCity;
	}

	public void setFimilyCity(String fimilyCity) {
		this.fimilyCity = fimilyCity;
	}

	public String getFimilyCityName() {
		return fimilyCityName;
	}

	public void setFimilyCityName(String fimilyCityName) {
		this.fimilyCityName = fimilyCityName;
	}

	public String getFimilyArea() {
		return fimilyArea;
	}

	public void setFimilyArea(String fimilyArea) {
		this.fimilyArea = fimilyArea;
	}

	public String getFimilyAreaName() {
		return fimilyAreaName;
	}

	public void setFimilyAreaName(String fimilyAreaName) {
		this.fimilyAreaName = fimilyAreaName;
	}

	public String getFimilyRoad() {
		return fimilyRoad;
	}

	public void setFimilyRoad(String fimilyRoad) {
		this.fimilyRoad = fimilyRoad;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getExpressTypeName() {
		return expressTypeName;
	}

	public void setExpressTypeName(String expressTypeName) {
		this.expressTypeName = expressTypeName;
	}

	public String getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(String monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
	}

	
}
