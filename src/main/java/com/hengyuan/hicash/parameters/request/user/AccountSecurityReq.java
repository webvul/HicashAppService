package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;
/**
 * 查询对应信息是否已经绑定或者是否已经验证
 * @author Eric.shi
 * @create date 2014-08-19
 *
 */
public class AccountSecurityReq extends RequestSequence {

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
