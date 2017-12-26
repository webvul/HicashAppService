package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;


/**
 * 手机端hicash白领资料查询1请求参数
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
public class CollarApp1MsgReq extends RequestSequence {

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
