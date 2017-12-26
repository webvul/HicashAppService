package com.hengyuan.hicash.dao.dict;

public enum CertificationState {
	
	CERI("认证中"),CERN("未认证"),CERY("已认证"),CERD("等待审核"),CERT("审核通过"),CERE("审核失败");
	
	
	private String dispValue;

	CertificationState(String dispValue) {
		this.dispValue = dispValue;
	}
	public String getDispValue() {
		return dispValue;
	}
	
}
