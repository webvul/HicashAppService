package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端白领提现申请1完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class CollarApp1UpdateReq extends RequestSequence {

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
	/** 毕业院校 */
	private String schoolName;
	/** 最高学历 */
	private String educationBg;
	/** 毕业时间-年 */
	private String endDateYear;

	/** 毕业时间-月 */
	private String endDateMonth;
	/** 学制 */
	private String educationalSystem;
	/** 婚姻状况 */
	private String matrimonySystem;
	/** 配偶姓名 */
	private String spouseName;

	/** 配偶手机号码 */
	private String spouseMobile;
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

	/**
	 * @return the schoolName
	 */
	public String getSchoolName() {
		return schoolName;
	}

	/**
	 * @param schoolName
	 *            the schoolName to set
	 */
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	/**
	 * @return the educationBg
	 */
	public String getEducationBg() {
		return educationBg;
	}

	/**
	 * @param educationBg
	 *            the educationBg to set
	 */
	public void setEducationBg(String educationBg) {
		this.educationBg = educationBg;
	}

	/**
	 * @return the endDateYear
	 */
	public String getEndDateYear() {
		return endDateYear;
	}

	/**
	 * @param endDateYear
	 *            the endDateYear to set
	 */
	public void setEndDateYear(String endDateYear) {
		this.endDateYear = endDateYear;
	}

	/**
	 * @return the endDateMonth
	 */
	public String getEndDateMonth() {
		return endDateMonth;
	}

	/**
	 * @param endDateMonth
	 *            the endDateMonth to set
	 */
	public void setEndDateMonth(String endDateMonth) {
		this.endDateMonth = endDateMonth;
	}

	/**
	 * @return the educationalSystem
	 */
	public String getEducationalSystem() {
		return educationalSystem;
	}

	/**
	 * @param educationalSystem
	 *            the educationalSystem to set
	 */
	public void setEducationalSystem(String educationalSystem) {
		this.educationalSystem = educationalSystem;
	}

	/**
	 * @return the matrimonySystem
	 */
	public String getMatrimonySystem() {
		return matrimonySystem;
	}

	/**
	 * @param matrimonySystem
	 *            the matrimonySystem to set
	 */
	public void setMatrimonySystem(String matrimonySystem) {
		this.matrimonySystem = matrimonySystem;
	}

	/**
	 * @return the spouseName
	 */
	public String getSpouseName() {
		return spouseName;
	}

	/**
	 * @param spouseName
	 *            the spouseName to set
	 */
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	/**
	 * @return the spouseMobile
	 */
	public String getSpouseMobile() {
		return spouseMobile;
	}

	/**
	 * @param spouseMobile
	 *            the spouseMobile to set
	 */
	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
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

}
