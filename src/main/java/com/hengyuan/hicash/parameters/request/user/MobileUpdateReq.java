package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 修改注册手机接口
 * @author Eric.shi
 * @create date 2014-07-22
 *
 */
public class MobileUpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/* 用户名 */
	private String userName;
	
	/* 原手机号 */
	private String oldMobile;
	
	/* 新手机号 */
	private String newMobile;
	
	/* 验证码 */
	private String certificationCode;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getOldMobile() {
		return oldMobile;
	}

	public void setOldMobile(String oldMobile) {
		this.oldMobile = oldMobile;
	}

	public String getNewMobile() {
		return newMobile;
	}

	public void setNewMobile(String newMobile) {
		this.newMobile = newMobile;
	}

	public String getCertificationCode() {
		return certificationCode;
	}

	public void setCertificationCode(String certificationCode) {
		this.certificationCode = certificationCode;
	}
	
}
