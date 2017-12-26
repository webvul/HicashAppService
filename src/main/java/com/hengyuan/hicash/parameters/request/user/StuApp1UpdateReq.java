package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请1完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp1UpdateReq extends RequestSequence {

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

	/** 学校ID */
	private String schoolId;

	/** 学校名称 */
	private String iptSchoolName;
	/** 学号 */
	private String iptStuId;
	/** 学历 */
	private String educationBk;

	/** 所在院系 */
	private String iptStuDepartment;

	/** 专业 */
	private String iptStuMajor;

	/** 入学时间-年 */
	private String studyTimeYear;

	/** 入学时间-月 */
	private String studyTimeMonth;

	/** 年级 */
	private String sltGrade;
	/** 学制 */
	private String schoolSystem;
	/** 学生类型 */
	private String stuType;
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

	// 学校地址省，城市
	// private String schoolProvince;
	// private String schoolCity;
	// private String schoolArea;

	// /**
	// * @return the schoolProvince
	// */
	// public String getSchoolProvince() {
	// return schoolProvince;
	// }
	// /**
	// * @param schoolProvince the schoolProvince to set
	// */
	// public void setSchoolProvince(String schoolProvince) {
	// this.schoolProvince = schoolProvince;
	// }
	// /**
	// * @return the schoolCity
	// */
	// public String getSchoolCity() {
	// return schoolCity;
	// }
	// /**
	// * @param schoolCity the schoolCity to set
	// */
	// public void setSchoolCity(String schoolCity) {
	// this.schoolCity = schoolCity;
	// }
	// /**
	// * @return the schoolArea
	// */
	// public String getSchoolArea() {
	// return schoolArea;
	// }
	// /**
	// * @param schoolArea the schoolArea to set
	// */
	// public void setSchoolArea(String schoolArea) {
	// this.schoolArea = schoolArea;
	// }

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
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
	 * @return the schoolId
	 */
	public String getSchoolId() {
		return schoolId;
	}

	/**
	 * @param schoolId
	 *            the schoolId to set
	 */
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	/**
	 * @return the iptSchoolName
	 */
	public String getIptSchoolName() {
		return iptSchoolName;
	}

	/**
	 * @param iptSchoolName
	 *            the iptSchoolName to set
	 */
	public void setIptSchoolName(String iptSchoolName) {
		this.iptSchoolName = iptSchoolName;
	}

	/**
	 * @return the iptStuId
	 */
	public String getIptStuId() {
		return iptStuId;
	}

	/**
	 * @param iptStuId
	 *            the iptStuId to set
	 */
	public void setIptStuId(String iptStuId) {
		this.iptStuId = iptStuId;
	}

	/**
	 * @return the educationBk
	 */
	public String getEducationBk() {
		return educationBk;
	}

	/**
	 * @param educationBk
	 *            the educationBk to set
	 */
	public void setEducationBk(String educationBk) {
		this.educationBk = educationBk;
	}

	/**
	 * @return the iptStuDepartment
	 */
	public String getIptStuDepartment() {
		return iptStuDepartment;
	}

	/**
	 * @param iptStuDepartment
	 *            the iptStuDepartment to set
	 */
	public void setIptStuDepartment(String iptStuDepartment) {
		this.iptStuDepartment = iptStuDepartment;
	}

	/**
	 * @return the iptStuMajor
	 */
	public String getIptStuMajor() {
		return iptStuMajor;
	}

	/**
	 * @param iptStuMajor
	 *            the iptStuMajor to set
	 */
	public void setIptStuMajor(String iptStuMajor) {
		this.iptStuMajor = iptStuMajor;
	}

	/**
	 * @return the studyTimeYear
	 */
	public String getStudyTimeYear() {
		return studyTimeYear;
	}

	/**
	 * @param studyTimeYear
	 *            the studyTimeYear to set
	 */
	public void setStudyTimeYear(String studyTimeYear) {
		this.studyTimeYear = studyTimeYear;
	}

	/**
	 * @return the studyTimeMonth
	 */
	public String getStudyTimeMonth() {
		return studyTimeMonth;
	}

	/**
	 * @param studyTimeMonth
	 *            the studyTimeMonth to set
	 */
	public void setStudyTimeMonth(String studyTimeMonth) {
		this.studyTimeMonth = studyTimeMonth;
	}

	/**
	 * @return the sltGrade
	 */
	public String getSltGrade() {
		return sltGrade;
	}

	/**
	 * @param sltGrade
	 *            the sltGrade to set
	 */
	public void setSltGrade(String sltGrade) {
		this.sltGrade = sltGrade;
	}

	/**
	 * @return the schoolSystem
	 */
	public String getSchoolSystem() {
		return schoolSystem;
	}

	/**
	 * @param schoolSystem
	 *            the schoolSystem to set
	 */
	public void setSchoolSystem(String schoolSystem) {
		this.schoolSystem = schoolSystem;
	}

	/**
	 * @return the stuType
	 */
	public String getStuType() {
		return stuType;
	}

	/**
	 * @param stuType
	 *            the stuType to set
	 */
	public void setStuType(String stuType) {
		this.stuType = stuType;
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
