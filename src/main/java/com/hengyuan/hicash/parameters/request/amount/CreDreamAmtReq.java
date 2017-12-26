package com.hengyuan.hicash.parameters.request.amount;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * 接口获取给用户授信的参数类
 * 
 * @author Laughing.peng
 * @create date 2014-07-22
 */
public class CreDreamAmtReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 真实姓名 */
	private String realname;

	/** 身份证号 */
	private String identity;

	/** 客户类型 */
	private String custType;

	/** 学历 */
	private String educational;

	/** 用户名 */
	private String username;

	/** 月收入 */
	private String monthIncome;

	public String getMonthIncome() {
		return monthIncome;
	}

	public void setMonthIncome(String monthIncome) {
		this.monthIncome = monthIncome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getEducational() {
		return educational;
	}

	public void setEducational(String educational) {
		this.educational = educational;
	}

}
