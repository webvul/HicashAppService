package com.hengyuan.hicash.parameters.request.amount;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 查询申请件进度 请求参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class AppScheduleReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
