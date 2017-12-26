package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端白领提现申请3完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp3UpdateReq extends RequestSequence {

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
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getEducationBg() {
		return educationBg;
	}
	public void setEducationBg(String educationBg) {
		this.educationBg = educationBg;
	}
	public String getEndDateYear() {
		return endDateYear;
	}
	public void setEndDateYear(String endDateYear) {
		this.endDateYear = endDateYear;
	}
	public String getEndDateMonth() {
		return endDateMonth;
	}
	public void setEndDateMonth(String endDateMonth) {
		this.endDateMonth = endDateMonth;
	}
	public String getEducationalSystem() {
		return educationalSystem;
	}
	public void setEducationalSystem(String educationalSystem) {
		this.educationalSystem = educationalSystem;
	}

	
	

}
