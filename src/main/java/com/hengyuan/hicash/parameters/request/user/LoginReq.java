package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 用户登录的请求参数
 * 
 * @author Cary.Liu
 * @ccreateDate 2015-04-20
 */
public class LoginReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 城市代码 */
	private String cityCode;

	/** 登录用户名 */
	private String userName;

	/** 登录密码 */
	private String passWord;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
