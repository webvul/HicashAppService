package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash秒贷金额查询
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class FastLoanFirstReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 登录用户名 */
	private String userName;
	private String tempAppNo;

	

	/**
	 * @return the tempAppNo
	 */
	public String getTempAppNo() {
		return tempAppNo;
	}

	/**
	 * @param tempAppNo the tempAppNo to set
	 */
	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
}
