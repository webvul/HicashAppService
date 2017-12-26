package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class AppAgainBasicReq extends RequestSequence {
	
	private static final long serialVersionUID = 1L;

	private String appTempNo;

	public String getAppTempNo() {
		return appTempNo;
	}

	public void setAppTempNo(String appTempNo) {
		this.appTempNo = appTempNo;
	}

}
