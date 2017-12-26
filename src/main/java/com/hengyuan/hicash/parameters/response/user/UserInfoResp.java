package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 是否为蓝领用户 resp
 * 
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
public class UserInfoResp extends ParmResponse {

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 用户类型 */
	private String custType;

	/** 是否在一个月内注册用户 */
	private String inOneMonthReg;

	/** 是否为蓝领用户 */
	private String isLanUserFlag;

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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

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

}
