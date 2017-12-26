package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 用户登录响应参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-20
 */
public class LoginResp extends ParmResponse {

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 身份证号码 */
	private String identityNo;

	/** 用户类型 */
	private String custType;

	/** 用户类型名称 */
	private String custTypeName;

	/** 用户手机号 */
	private String mobile;

	/** 最高学历 */
	private String education;

	/** 最高学历名称 */
	private String educationName;

	/** 是否已授信额度 */
	private String limitFlag;

	/** 额度值 */
	private String limitAmount;

	/** 是否激活账户 */
	private String accountFlag;

	/** 认证标志 */
	private String token;

	/** 页面索引 */
	private String pageIndex;

	/** 登陆城市 */
	private String cityCode;

	/** 是否是二次营销 */
	private String isDoubleSales;

	/** 是否在一个月内注册用户 */
	private String inOneMonthReg;

	/** 是否为蓝领用户 */
	private String isLanUserFlag;

	public String getInOneMonthReg() {
		return inOneMonthReg;
	}

	public void setInOneMonthReg(String inOneMonthReg) {
		this.inOneMonthReg = inOneMonthReg;
	}

	public String getIsLanUserFlag() {
		return isLanUserFlag;
	}

	public void setIsLanUserFlag(String isLanUserFlag) {
		this.isLanUserFlag = isLanUserFlag;
	}

	public String getIsDoubleSales() {
		return isDoubleSales;
	}

	public void setIsDoubleSales(String isDoubleSales) {
		this.isDoubleSales = isDoubleSales;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getEducationName() {
		return educationName;
	}

	public void setEducationName(String educationName) {
		this.educationName = educationName;
	}

	public String getLimitFlag() {
		return limitFlag;
	}

	public void setLimitFlag(String limitFlag) {
		this.limitFlag = limitFlag;
	}

	public String getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(String limitAmount) {
		this.limitAmount = limitAmount;
	}

	public String getAccountFlag() {
		return accountFlag;
	}

	public void setAccountFlag(String accountFlag) {
		this.accountFlag = accountFlag;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

}
