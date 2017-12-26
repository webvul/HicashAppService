package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请1
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 登录用户名 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
