package com.hengyuan.hicash.parameters.request;

/**
 * hicash用户名是否已经存在接收参数集合
 * @author Andy.Niu
 *
 */
public class UserExistReq extends RequestSequence{

	private static final long serialVersionUID = 1L;
	
	
	/** 用户名 */
	public String userName;

	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
