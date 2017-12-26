package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * 创建申请件接收参数对象
 * 
 * @author Mary.Luo
 * @create date 2014-07-22
 * 
 */
public class CreateLoanPlanReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 申请件流水号 */
	private String appPayNo;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppNo() {
		return appPayNo;
	}

	public void setAppNo(String appPayNo) {
		this.appPayNo = appPayNo;
	}
	

}
