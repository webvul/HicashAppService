package com.hengyuan.hicash.entity.remote;

public class AuthorizeRespParam {

	private String success;

	private AuthorizeData data;

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}

	public AuthorizeData getData() {
		return data;
	}

	public void setData(AuthorizeData data) {
		this.data = data;
	}

}
