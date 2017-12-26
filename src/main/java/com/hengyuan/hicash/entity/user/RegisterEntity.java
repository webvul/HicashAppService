package com.hengyuan.hicash.entity.user;

/**
 * 用户注册查询数据存放
 * 
 * @author Cary.Liu
 * @create date 2014-07-21
 */
public class RegisterEntity {

	/** 用户是否存在 */
	private boolean isUsernameExist;

	public boolean isUsernameExist() {
		return isUsernameExist;
	}

	public void setUsernameExist(boolean isUsernameExist) {
		this.isUsernameExist = isUsernameExist;
	}

	
}
