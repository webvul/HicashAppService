package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * @author xing.yuan
 * @date 2017年12月21日 下午3:29:50
 * 类说明
 */
public class RedpacketIsValidReq extends RequestSequence{
	
	private String moblieNo;
	
	private String password;
	
	private String type;


	public String getMoblieNo() {
		return moblieNo;
	}

	public void setMoblieNo(String moblieNo) {
		this.moblieNo = moblieNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	

}
