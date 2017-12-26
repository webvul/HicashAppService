package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端白领提现申请1查询返回信息
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class CollarApp1MsgResp extends ParmResponse {

	/** 婚姻状况 */
	private String maritalStatus;

	/** 婚姻状况名称 */
	private String maritalStatusName;

	/** 配偶姓名 */
	private String spouseName;
	/** 配偶手机号码 */
	private String spouseMobile;

	/** 毕业学校 */
	private String school;

	/** 最高学历 */
	private String education;

	/** 最高学历名称 */
	private String educationName;

	/** 毕业时间--年 */
	private String gradYear;

	/** 毕业时间--月 */
	private String gradMonth;

	/** 学制 */
	private String fulltime;

	/** 学制名称 */
	private String fulltimeName;

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

	/**
	 * @return the maritalStatusName
	 */
	public String getMaritalStatusName() {
		return maritalStatusName;
	}

	/**
	 * @param maritalStatusName
	 *            the maritalStatusName to set
	 */
	public void setMaritalStatusName(String maritalStatusName) {
		this.maritalStatusName = maritalStatusName;
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
	 * @return the school
	 */
	public String getSchool() {
		return school;
	}

	/**
	 * @param school
	 *            the school to set
	 */
	public void setSchool(String school) {
		this.school = school;
	}

	/**
	 * @return the education
	 */
	public String getEducation() {
		return education;
	}

	/**
	 * @param education
	 *            the education to set
	 */
	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the educationName
	 */
	public String getEducationName() {
		return educationName;
	}

	/**
	 * @param educationName
	 *            the educationName to set
	 */
	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	/**
	 * @return the gradYear
	 */
	public String getGradYear() {
		return gradYear;
	}

	/**
	 * @param gradYear
	 *            the gradYear to set
	 */
	public void setGradYear(String gradYear) {
		this.gradYear = gradYear;
	}

	/**
	 * @return the gradMonth
	 */
	public String getGradMonth() {
		return gradMonth;
	}

	/**
	 * @param gradMonth
	 *            the gradMonth to set
	 */
	public void setGradMonth(String gradMonth) {
		this.gradMonth = gradMonth;
	}

	/**
	 * @return the fulltime
	 */
	public String getFulltime() {
		return fulltime;
	}

	/**
	 * @param fulltime
	 *            the fulltime to set
	 */
	public void setFulltime(String fulltime) {
		this.fulltime = fulltime;
	}

	/**
	 * @return the fulltimeName
	 */
	public String getFulltimeName() {
		return fulltimeName;
	}

	/**
	 * @param fulltimeName
	 *            the fulltimeName to set
	 */
	public void setFulltimeName(String fulltimeName) {
		this.fulltimeName = fulltimeName;
	}

	/** 学校id */
	// private String schoolid;

}
