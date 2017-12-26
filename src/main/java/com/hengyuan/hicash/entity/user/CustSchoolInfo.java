package com.hengyuan.hicash.entity.user;

/**
 * 学生用户保存学校信息
 * 
 * @author Cary.Liu
 * @create date 2014-07-24
 * @table cust_school_info
 */
public class CustSchoolInfo {

	private int id; // id
	private String username;// username,
	private String schoolName;// school_name 学校名称
	private String educationalSystem;// educational_System 学制
	private String educationalBackground;// educational_background 学历
	private String admissionTime;// admission_time 入学时间
	private String grade;// grade 年级
	private String studentId;// student_id 学号
	private String department;// department 院系
	private String major;// major 专业
	private String studentType;// student_type 学生类型

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

	public String getEducationalSystem() {
		return educationalSystem;
	}

	public void setEducationalSystem(String educationalSystem) {
		this.educationalSystem = educationalSystem;
	}

	public String getEducationalBackground() {
		return educationalBackground;
	}

	public void setEducationalBackground(String educationalBackground) {
		this.educationalBackground = educationalBackground;
	}

	public String getAdmissionTime() {
		return admissionTime;
	}

	public void setAdmissionTime(String admissionTime) {
		this.admissionTime = admissionTime;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getStudentType() {
		return studentType;
	}

	public void setStudentType(String studentType) {
		this.studentType = studentType;
	}

}
