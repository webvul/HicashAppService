package com.hengyuan.hicash.entity.user;

/**
 * 白领保存单位信息实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-24
 * @table cust_unit_info
 */
public class CustUnitInfo {

	private int id;
	private String username;
	private String unitName;// unitName 单位名称
	private String sectors;// sectors 行业类别
	private String jobNumber;// job_Number工号
	private String unit_Properties;// unit_Properties 单位性质
	private String entryTime;// entry_time 入职时间
	private String workExperience;// workExperience 总工作年限
	private String jobNature;// job_nature 入职部门
	private String jobDuties;// job_duties 职务
	private String unitTelArea;// unit_tel_area 单位电话-区号
	private String unitTel;// unit_tel 单位电话-号码

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getSectors() {
		return sectors;
	}

	public void setSectors(String sectors) {
		this.sectors = sectors;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getUnit_Properties() {
		return unit_Properties;
	}

	public void setUnit_Properties(String unit_Properties) {
		this.unit_Properties = unit_Properties;
	}

	public String getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(String entryTime) {
		this.entryTime = entryTime;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public String getJobNature() {
		return jobNature;
	}

	public void setJobNature(String jobNature) {
		this.jobNature = jobNature;
	}

	public String getJobDuties() {
		return jobDuties;
	}

	public void setJobDuties(String jobDuties) {
		this.jobDuties = jobDuties;
	}

	public String getUnitTelArea() {
		return unitTelArea;
	}

	public void setUnitTelArea(String unitTelArea) {
		this.unitTelArea = unitTelArea;
	}

	public String getUnitTel() {
		return unitTel;
	}

	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}

}
