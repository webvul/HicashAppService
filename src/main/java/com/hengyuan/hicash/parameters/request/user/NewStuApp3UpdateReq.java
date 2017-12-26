package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请3完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp3UpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 修改类型 */
	private String updateType;

	/** 申请件流水号 */
	private String appNo;
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
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getIptSchoolName() {
		return iptSchoolName;
	}
	public void setIptSchoolName(String iptSchoolName) {
		this.iptSchoolName = iptSchoolName;
	}
	public String getIptStuId() {
		return iptStuId;
	}
	public void setIptStuId(String iptStuId) {
		this.iptStuId = iptStuId;
	}
	public String getEducationBk() {
		return educationBk;
	}
	public void setEducationBk(String educationBk) {
		this.educationBk = educationBk;
	}
	public String getIptStuDepartment() {
		return iptStuDepartment;
	}
	public void setIptStuDepartment(String iptStuDepartment) {
		this.iptStuDepartment = iptStuDepartment;
	}
	public String getIptStuMajor() {
		return iptStuMajor;
	}
	public void setIptStuMajor(String iptStuMajor) {
		this.iptStuMajor = iptStuMajor;
	}
	public String getStudyTimeYear() {
		return studyTimeYear;
	}
	public void setStudyTimeYear(String studyTimeYear) {
		this.studyTimeYear = studyTimeYear;
	}
	public String getStudyTimeMonth() {
		return studyTimeMonth;
	}
	public void setStudyTimeMonth(String studyTimeMonth) {
		this.studyTimeMonth = studyTimeMonth;
	}
	public String getSltGrade() {
		return sltGrade;
	}
	public void setSltGrade(String sltGrade) {
		this.sltGrade = sltGrade;
	}
	public String getSchoolSystem() {
		return schoolSystem;
	}
	public void setSchoolSystem(String schoolSystem) {
		this.schoolSystem = schoolSystem;
	}
	public String getStuType() {
		return stuType;
	}
	public void setStuType(String stuType) {
		this.stuType = stuType;
	}
	
	
	

	
}
