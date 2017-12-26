package com.hengyuan.hicash.entity.user;

/**
 * 白领保存个人信息实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-24
 * @table cust_personal_info
 */
public class CustPersonalInfo {

	private int id;
	private String username;
	private String schoolName;// school_Name 毕业院校
	private String graduationDate;// graduation_Date 毕业时间
	private String educationBackground;// education_Background 最高学历
	private String educationalSystem;// educational_System 学制
	private String matrimonySystem;// matrimony_System 婚姻状况
	private String sltHousingSystem;// sltHousing_System 住房状况

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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getGraduationDate() {
		return graduationDate;
	}

	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}

	public String getEducationBackground() {
		return educationBackground;
	}

	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}

	public String getEducationalSystem() {
		return educationalSystem;
	}

	public void setEducationalSystem(String educationalSystem) {
		this.educationalSystem = educationalSystem;
	}

	public String getMatrimonySystem() {
		return matrimonySystem;
	}

	public void setMatrimonySystem(String matrimonySystem) {
		this.matrimonySystem = matrimonySystem;
	}

	public String getSltHousingSystem() {
		return sltHousingSystem;
	}

	public void setSltHousingSystem(String sltHousingSystem) {
		this.sltHousingSystem = sltHousingSystem;
	}

}
