package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/** 
 * @author blianke.qin
 * 2017年1月9日 下午9:51:13
 * 类说明 
 */
public class ShowWaitTimeReq extends RequestSequence{

	/**
	 * 
	 */
	private static final long serialVersionUID = 159265737535905783L;
	
	/** 申请单号 */
	private String appNo;
	
	private String authorization;
	
	public String getAuthorization() {
		return authorization;
	}

	public void setAuthorization(String authorization) {
		this.authorization = authorization;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	
}
