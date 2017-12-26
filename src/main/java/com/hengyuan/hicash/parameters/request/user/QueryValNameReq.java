package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/** 
 * @author dong.liu 
 * 2017-9-5 下午6:11:50
 * 类说明 
 */
public class QueryValNameReq extends RequestSequence{
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
