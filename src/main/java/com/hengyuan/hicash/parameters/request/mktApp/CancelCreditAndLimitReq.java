package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/** 
 * @author dong.liu 
 * 2017-4-7 下午2:43:42
 * 类说明 
 */
public class CancelCreditAndLimitReq extends RequestSequence{
	
	private String app_no;//流水号

	public String getApp_no() {
		return app_no;
	}

	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	
}
