package com.hengyuan.hicash.parameters.request.order;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author xuexin 
 *
 * @date 2017年7月11日 18:55:53
 */
public class ApplicationPayReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 流水号 */
	private String appNo;

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	

}
