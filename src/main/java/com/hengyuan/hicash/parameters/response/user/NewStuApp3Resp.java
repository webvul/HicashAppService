package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请3返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp3Resp extends ParmResponse {

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
	
	/** 客户类型 */
	private String custType;

	/** 客户类型名称 */
	private String custTypeName;

	public String getSchoolid() {
		return schoolid;
	}

	public void setSchoolid(String schoolid) {
		this.schoolid = schoolid;
	}

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

	public String getEducationalName() {
		return educationalName;
	}

	public void setEducationalName(String educationalName) {
		this.educationalName = educationalName;
	}

	public String getFaculties() {
		return faculties;
	}

	public void setFaculties(String faculties) {
		this.faculties = faculties;
	}

	public String getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}

	public String getEntryYear() {
		return entryYear;
	}

	public void setEntryYear(String entryYear) {
		this.entryYear = entryYear;
	}

	public String getEntryMonth() {
		return entryMonth;
	}

	public void setEntryMonth(String entryMonth) {
		this.entryMonth = entryMonth;
	}

	public String getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(String studentClass) {
		this.studentClass = studentClass;
	}

	public String getStudentClassName() {
		return studentClassName;
	}

	public void setStudentClassName(String studentClassName) {
		this.studentClassName = studentClassName;
	}

	public String getFulltime() {
		return fulltime;
	}

	public void setFulltime(String fulltime) {
		this.fulltime = fulltime;
	}

	public String getFulltimeName() {
		return fulltimeName;
	}

	public void setFulltimeName(String fulltimeName) {
		this.fulltimeName = fulltimeName;
	}

	public String getStuType() {
		return stuType;
	}

	public void setStuType(String stuType) {
		this.stuType = stuType;
	}

	public String getStuTypeName() {
		return stuTypeName;
	}

	public void setStuTypeName(String stuTypeName) {
		this.stuTypeName = stuTypeName;
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

	

	
	

}
