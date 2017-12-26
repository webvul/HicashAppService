package com.hengyuan.hicash.entity.user;

import java.util.Date;

/**
 * 修改注册手机查询数据存放类
 * 
 * @author Eric
 * @create date 2014-07-23
 * 
 */
public class MobileUpdateEntity {
	
	/* 用户名 */
	private String username;
	
	/* 认证类型 */
	private String certificationType;
	
	/* 认证状态 */
	private String status;
	
	private Boolean showp;
	
	private Boolean isExpire;
	
	/* 过期时间 */
	private Date examineTime;
	
	private String operator;
	
	private String examineDesc;
	
	/* 手机号码 */
	private String mobile;
	
	/* 密码 */
	private String password;
	
	//手机验证码
	private String mobileValidateCode;
	
	//手机验证码有效期
	private String mobileValidateCodeValidTime;
	
	// 接收验证码的手机号码
	private String amountValidateTempMobile;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCertificationType() {
		return certificationType;
	}

	public void setCertificationType(String certificationType) {
		this.certificationType = certificationType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getShowp() {
		return showp;
	}

	public void setShowp(Boolean showp) {
		this.showp = showp;
	}

	public Boolean getIsExpire() {
		return isExpire;
	}

	public void setIsExpire(Boolean isExpire) {
		this.isExpire = isExpire;
	}

	public Date getExamineTime() {
		return examineTime;
	}

	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getExamineDesc() {
		return examineDesc;
	}

	public void setExamineDesc(String examineDesc) {
		this.examineDesc = examineDesc;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMobileValidateCode() {
		return mobileValidateCode;
	}

	public void setMobileValidateCode(String mobileValidateCode) {
		this.mobileValidateCode = mobileValidateCode;
	}

	public String getMobileValidateCodeValidTime() {
		return mobileValidateCodeValidTime;
	}

	public void setMobileValidateCodeValidTime(String mobileValidateCodeValidTime) {
		this.mobileValidateCodeValidTime = mobileValidateCodeValidTime;
	}

	public String getAmountValidateTempMobile() {
		return amountValidateTempMobile;
	}

	public void setAmountValidateTempMobile(String amountValidateTempMobile) {
		this.amountValidateTempMobile = amountValidateTempMobile;
	}
	
}
