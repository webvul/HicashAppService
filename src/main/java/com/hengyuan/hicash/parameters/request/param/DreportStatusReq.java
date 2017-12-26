package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
* @author dong.liu 
* 2017-12-20 下午6:15:10
* 类说明:
 */
public class DreportStatusReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 临时订单号*/
	private String tempAppNo;
	
	private String authen;//认证项 1:
	
	private String authenStatus;//认证状态

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getAuthen() {
		return authen;
	}

	public void setAuthen(String authen) {
		this.authen = authen;
	}

	public String getAuthenStatus() {
		return authenStatus;
	}

	public void setAuthenStatus(String authenStatus) {
		this.authenStatus = authenStatus;
	} 

	
	
	
	
}
