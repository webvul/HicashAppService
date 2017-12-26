package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class CheckPersonFaceReq extends RequestSequence {

	private static final long serialVersionUID = 5486690404923696137L;
	/** 临时单号 */
	private String tempNo; 
	/** 身份证 ,老版本*/
	private String idNo;
	//新版本
//	private String appNo;//正式单号
//	private String userName;//用户名
	public String getTempNo() {
		return tempNo;
	}
	public void setTempNo(String tempNo) {
		this.tempNo = tempNo;
	}
	public String getIdNo() {
		return idNo;
	}
	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}
	
//	public String getAppNo() {
//		return appNo;
//	}
//	public void setAppNo(String appNo) {
//		this.appNo = appNo;
//	}
//	public String getUserName() {
//		return userName;
//	}
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
	



	

	
	
}
