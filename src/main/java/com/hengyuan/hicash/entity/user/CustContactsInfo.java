package com.hengyuan.hicash.entity.user;

/**
 * 用户保存联系人信息实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-24
 * @table cust_contacts_info
 */
public class CustContactsInfo {

	private int id;
	private String username;// username
	private String immediateName;// immediate_Name 直系亲属姓名
	private String immediateRelation;// immediate_Relation直系亲属关系
	private String immediateJob;// immediate_Job 直系亲属工作单位
	private String immediateMobile;// immediate_Mobile 直系亲属手机
	private String immediateAdress;// immediate_Adress 直系亲属地址
	private String emergencyName;// emergency_Name 紧急联系人性名
	private String emergencyRelation;// emergency_Relation 紧急联系人关系
	private String emergencyJob;// emergency_Job 紧急联系人工作
	private String emergencyMobile;// emergency_Mobile 紧急联系人手机
	private String emergencyAdress;// emergency_Adress 紧急联系人地址

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

	public String getImmediateName() {
		return immediateName;
	}

	public void setImmediateName(String immediateName) {
		this.immediateName = immediateName;
	}

	public String getImmediateRelation() {
		return immediateRelation;
	}

	public void setImmediateRelation(String immediateRelation) {
		this.immediateRelation = immediateRelation;
	}

	public String getImmediateJob() {
		return immediateJob;
	}

	public void setImmediateJob(String immediateJob) {
		this.immediateJob = immediateJob;
	}

	public String getImmediateMobile() {
		return immediateMobile;
	}

	public void setImmediateMobile(String immediateMobile) {
		this.immediateMobile = immediateMobile;
	}

	public String getImmediateAdress() {
		return immediateAdress;
	}

	public void setImmediateAdress(String immediateAdress) {
		this.immediateAdress = immediateAdress;
	}

	public String getEmergencyName() {
		return emergencyName;
	}

	public void setEmergencyName(String emergencyName) {
		this.emergencyName = emergencyName;
	}

	public String getEmergencyRelation() {
		return emergencyRelation;
	}

	public void setEmergencyRelation(String emergencyRelation) {
		this.emergencyRelation = emergencyRelation;
	}

	public String getEmergencyJob() {
		return emergencyJob;
	}

	public void setEmergencyJob(String emergencyJob) {
		this.emergencyJob = emergencyJob;
	}

	public String getEmergencyMobile() {
		return emergencyMobile;
	}

	public void setEmergencyMobile(String emergencyMobile) {
		this.emergencyMobile = emergencyMobile;
	}

	public String getEmergencyAdress() {
		return emergencyAdress;
	}

	public void setEmergencyAdress(String emergencyAdress) {
		this.emergencyAdress = emergencyAdress;
	}

}
