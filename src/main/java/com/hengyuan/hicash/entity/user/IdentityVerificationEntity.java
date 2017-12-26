package com.hengyuan.hicash.entity.user;

/**
 * 身份证验证查询结果存放类
 * 
 * @author Laughing.Peng
 * @create date 2014-07-21
 * 
 */
public class IdentityVerificationEntity {
    
	/**身份证号*/
	private String  identity;
	
	/**用户名*/
	private String  username;

	public String getIdentity() {
		return identity;
	}

	public void setIdentity(String identity) {
		this.identity = identity;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	
}
