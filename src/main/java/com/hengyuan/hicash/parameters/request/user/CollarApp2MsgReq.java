package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;
/**
 * 白领获取资料接口
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 *
 */
public class CollarApp2MsgReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 登录用户名 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	

}
