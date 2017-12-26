package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请2完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp2UpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 修改类型 */
	private String updateType;

	/** 申请件流水号 */
	private String appNo;
	/*
	 * 联系人信息
	 */

	/** 直系亲属(姓名) */
	private String familyName;

	/** 紧急联系人(姓名) */
	private String contactName;

	/** 直系亲属(关系) */
	private String familyRelation;

	/** 紧急联系人(关系) */
	private String contactRelation;

	/** 直系亲属(工作单位) */
	private String familyWorkUnit;

//	/** 紧急联系人(工作单位) */
//	private String contactWorkUnit;

	/** 直系亲属(手机) */
	private String familyPhone;

	/** 紧急联系人(手机) */
	private String contactPhone;

	/** 直系亲属(地址) */
	private String familyAddress;

	/** 配偶姓名 */
	private String spouseName;
	/** 配偶手机号码 */
	private String spouseMobile;
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
	public String getFamilyName() {
		return familyName;
	}
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getFamilyRelation() {
		return familyRelation;
	}
	public void setFamilyRelation(String familyRelation) {
		this.familyRelation = familyRelation;
	}
	public String getContactRelation() {
		return contactRelation;
	}
	public void setContactRelation(String contactRelation) {
		this.contactRelation = contactRelation;
	}
	public String getFamilyWorkUnit() {
		return familyWorkUnit;
	}
	public void setFamilyWorkUnit(String familyWorkUnit) {
		this.familyWorkUnit = familyWorkUnit;
	}
	public String getFamilyPhone() {
		return familyPhone;
	}
	public void setFamilyPhone(String familyPhone) {
		this.familyPhone = familyPhone;
	}
	public String getContactPhone() {
		return contactPhone;
	}
	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public String getSpouseMobile() {
		return spouseMobile;
	}
	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}
	
	
	

	
}
