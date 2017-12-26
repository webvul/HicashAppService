package com.hengyuan.hicash.parameters.request.activity;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 新年红包抽取 req
 * 
 * @author Cary.Liu
 * @createDate 2016-02-01
 *
 */
public class NewYearRedPackReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
