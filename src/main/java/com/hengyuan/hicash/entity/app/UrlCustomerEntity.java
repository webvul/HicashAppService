package com.hengyuan.hicash.entity.app;

/**
 * 登陆用户查询实体
 * @author Mary.luo
 * @create date 2014-08-11
 */
public class UrlCustomerEntity {

	/** 用户名 */
	private String urlUserName;

	/** 用户密码 */
	private String urlPassWord;
	
	/** 加密salt */
	private String salt;

	public String getUrlUserName() {
		return urlUserName;
	}

	public void setUrlUserName(String urlUserName) {
		this.urlUserName = urlUserName;
	}

	public String getUrlPassWord() {
		return urlPassWord;
	}

	public void setUrlPassWord(String urlPassWord) {
		this.urlPassWord = urlPassWord;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}
	

}
