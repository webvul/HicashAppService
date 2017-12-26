package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请1返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp1Resp extends ParmResponse {

	/** 返回代码 */
	// private String resultCode;
	/** 客户类型 */
	private String custType;
	/** 客户类型名称 */
	private String custTypeName;
	/** 邮箱地址 */
	private String emailAdress;
	/** 学校id */
	private String schoolid;

	/** 学校名称 */
	private String schoolName;
	/** 学号 */
	private String studentId;
	/** 在读学历 */
	private String educational;

	private String educationalName;

	/** 所在院系 */
	private String faculties;
	/** 专业 */
	private String specialty;

	/** 入学时间--年 */
	private String entryYear;

	/** 入学时间--月 */
	private String entryMonth;
	/** 年级 */
	private String studentClass;

	private String studentClassName;
	/** 学制 */
	private String fulltime;

	private String fulltimeName;
	/** 学生类型 */
	private String stuType;

	private String stuTypeName;
	/** 学校地址--省 */
	private String schoolPro;

	private String schoolProName;

	/** 民族 */
	private String nation;

	/** 身份证有效期开始时间 */
	private String idCardValStartDate;

	/** 身份证有效期结束时间 */
	private String idCardValEndDate;

	/** 份证有效期(期限) */
	private String idCardValidity;

	// /** 学校地址--市 */
	// private String schoolCity;
	//
	// private String schoolCityName;

	private String qqNumber;
	private String homePhoneNum;
	private String homePhoneArea;
	
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
	 * @return the emailAdress
	 */
	public String getEmailAdress() {
		return emailAdress;
	}

	/**
	 * @param emailAdress
	 *            the emailAdress to set
	 */
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	/**
	 * @return the custTypeName
	 */
	public String getCustTypeName() {
		return custTypeName;
	}

	/**
	 * @param custTypeName
	 *            the custTypeName to set
	 */
	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	/**
	 * @return the schoolid
	 */
	public String getSchoolid() {
		return schoolid;
	}

	/**
	 * @param schoolid
	 *            the schoolid to set
	 */
	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
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
	 * @return the studentId
	 */
	public String getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId
	 *            the studentId to set
	 */
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the educational
	 */
	public String getEducational() {
		return educational;
	}

	/**
	 * @param educational
	 *            the educational to set
	 */
	public void setEducational(String educational) {
		this.educational = educational;
	}

	/**
	 * @return the educationalName
	 */
	public String getEducationalName() {
		return educationalName;
	}

	/**
	 * @param educationalName
	 *            the educationalName to set
	 */
	public void setEducationalName(String educationalName) {
		this.educationalName = educationalName;
	}

	/**
	 * @return the faculties
	 */
	public String getFaculties() {
		return faculties;
	}

	/**
	 * @param faculties
	 *            the faculties to set
	 */
	public void setFaculties(String faculties) {
		this.faculties = faculties;
	}

	/**
	 * @return the specialty
	 */
	public String getSpecialty() {
		return specialty;
	}

	/**
	 * @param specialty
	 *            the specialty to set
	 */
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	/**
	 * @return the entryYear
	 */
	public String getEntryYear() {
		return entryYear;
	}

	/**
	 * @param entryYear
	 *            the entryYear to set
	 */
	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}

	/**
	 * @return the entryMonth
	 */
	public String getEntryMonth() {
		return entryMonth;
	}

	/**
	 * @param entryMonth
	 *            the entryMonth to set
	 */
	public void setEntryMonth(String entryMonth) {
		this.entryMonth = entryMonth;
	}

	/**
	 * @return the studentClass
	 */
	public String getStudentClass() {
		return studentClass;
	}

	/**
	 * @param studentClass
	 *            the studentClass to set
	 */
	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	/**
	 * @return the studentClassName
	 */
	public String getStudentClassName() {
		return studentClassName;
	}

	/**
	 * @param studentClassName
	 *            the studentClassName to set
	 */
	public void setStudentClassName(String studentClassName) {
		this.studentClassName = studentClassName;
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
	 * @return the stuTypeName
	 */
	public String getStuTypeName() {
		return stuTypeName;
	}

	/**
	 * @param stuTypeName
	 *            the stuTypeName to set
	 */
	public void setStuTypeName(String stuTypeName) {
		this.stuTypeName = stuTypeName;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType
	 *            the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the schoolPro
	 */
	public String getSchoolPro() {
		return schoolPro;
	}

	/**
	 * @param schoolPro
	 *            the schoolPro to set
	 */
	public void setSchoolPro(String schoolPro) {
		this.schoolPro = schoolPro;
	}

	/**
	 * @return the schoolProName
	 */
	public String getSchoolProName() {
		return schoolProName;
	}

	/**
	 * @param schoolProName
	 *            the schoolProName to set
	 */
	public void setSchoolProName(String schoolProName) {
		this.schoolProName = schoolProName;
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

	// /**
	// * @return the schoolCity
	// */
	// public String getSchoolCity() {
	// return schoolCity;
	// }
	//
	// /**
	// * @param schoolCity the schoolCity to set
	// */
	// public void setSchoolCity(String schoolCity) {
	// this.schoolCity = schoolCity;
	// }
	//
	// /**
	// * @return the schoolCityName
	// */
	// public String getSchoolCityName() {
	// return schoolCityName;
	// }
	//
	// /**
	// * @param schoolCityName the schoolCityName to set
	// */
	// public void setSchoolCityName(String schoolCityName) {
	// this.schoolCityName = schoolCityName;
	// }
	//

}
