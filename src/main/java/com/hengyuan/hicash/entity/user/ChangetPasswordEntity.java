package com.hengyuan.hicash.entity.user;

/**
 * 修改密码查询数据存放类
 * 
 * @author Cary.Liu
 * @create date 2014-07-17
 * 
 */
public class ChangetPasswordEntity {

	/** 原始密码 */
	private String oldPassword;

	/** 原始加密 */
	private String oldSalt;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getOldSalt() {
		return oldSalt;
	}

	public void setOldSalt(String oldSalt) {
		this.oldSalt = oldSalt;
	}

}
