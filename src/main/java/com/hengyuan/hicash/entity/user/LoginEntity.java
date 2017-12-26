package com.hengyuan.hicash.entity.user;

/**
 * 用户登录查询数据存放
 * 
 * @author Cary.Liu
 * @createDate 2015-04-20
 */
public class LoginEntity {
	
	private String id ;

	private String userName;

	/** 是否存在 */
	private boolean existFlag;

	/** 登录密码 */
	private String oldPassword;

	/** 加密字符串 */
	private String oldSalt;

	/** 连续登录失败次数 */
	private int lockedNum;

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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

	public boolean isExistFlag() {
		return existFlag;
	}

	public void setExistFlag(boolean existFlag) {
		this.existFlag = existFlag;
	}

	public int getLockedNum() {
		return lockedNum;
	}

	public void setLockedNum(int lockedNum) {
		this.lockedNum = lockedNum;
	}

}
