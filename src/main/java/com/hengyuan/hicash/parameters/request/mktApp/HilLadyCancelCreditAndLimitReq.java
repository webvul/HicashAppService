package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
* @author dong.liu 
* 2017-5-12 下午2:29:47
* 类说明
 */
public class HilLadyCancelCreditAndLimitReq extends RequestSequence{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String app_no;//流水号

	public String getApp_no() {
		return app_no;
	}

	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	
}
