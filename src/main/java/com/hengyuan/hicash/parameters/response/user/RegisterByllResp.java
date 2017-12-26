package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 蓝领活动注册 resp
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterByllResp extends ParmResponse {

	/** 用户名 */
	private String userName;

	/** 用户手机号 */
	private String mobileNo;

	/** 额度 */
	private String amount;

	/** 真实姓名 */
	private String realName;

	/** 身份证号码 */
	private String identityNo;

	/** 用户类型 */
	private String custType;

	/** 用户类型名称 */
	private String custTypeName;

	/** 是否已授信额度 */
	private String limitFlag;

	/** 是否激活账户 */
	private String accountFlag;

	/** 用户登录认证 */
	private String token;
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
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

	public String getLimitFlag() {
		return limitFlag;
	}

	public void setLimitFlag(String limitFlag) {
		this.limitFlag = limitFlag;
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

}
