package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请1完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp1UpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 修改类型 */
	private String updateType;

	/** 申请件流水号 */
	private String appNo;
	/*
	 * 学校信息
	 */

	private String realName;
	
	private String identiyNo;
	
	/** 家庭地址-省 */
	private String homeProvince;

	/** 家庭地址-市 */
	private String homeCity;

	/** 家庭地址-区 */
	private String homeDistrict;

	/** 家庭地址-详细 */
	private String homeDetails;
	/** 合同快递地址-省 */
	private String expressProvince;

	/** 合同快递地址-市 */
	private String expressCity;

	/** 合同快递地址-区 */
	private String expressDistrict;

	/** 合同快递地址-详细 */
	private String expressDetails;
	/** 合同邮寄地址类型 */
	private String expressAddressType;
	/** 学校地址-省 */
	private String schoolProvince;
	/** 学校地址-城市*/
	private String schoolCity;
	/** 学校地址-区 */
	private String schoolDistrict;

	/** 学校地址-详细 */
	private String schoolDetails;
	
	
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

	public String getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getAppNo() {
		return appNo;
	}

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

	public String getExpressProvince() {
		return expressProvince;
	}

	public void setExpressProvince(String expressProvince) {
		this.expressProvince = expressProvince;
	}

	public String getExpressCity() {
		return expressCity;
	}

	public void setExpressCity(String expressCity) {
		this.expressCity = expressCity;
	}

	public String getExpressDistrict() {
		return expressDistrict;
	}

	public void setExpressDistrict(String expressDistrict) {
		this.expressDistrict = expressDistrict;
	}

	public String getExpressDetails() {
		return expressDetails;
	}

	public void setExpressDetails(String expressDetails) {
		this.expressDetails = expressDetails;
	}

	public String getExpressAddressType() {
		return expressAddressType;
	}

	public void setExpressAddressType(String expressAddressType) {
		this.expressAddressType = expressAddressType;
	}

	public String getSchoolProvince() {
		return schoolProvince;
	}

	public void setSchoolProvince(String schoolProvince) {
		this.schoolProvince = schoolProvince;
	}

	public String getSchoolCity() {
		return schoolCity;
	}

	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	public String getSchoolDistrict() {
		return schoolDistrict;
	}

	public void setSchoolDistrict(String schoolDistrict) {
		this.schoolDistrict = schoolDistrict;
	}

	public String getSchoolDetails() {
		return schoolDetails;
	}

	public void setSchoolDetails(String schoolDetails) {
		this.schoolDetails = schoolDetails;
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

	
}
