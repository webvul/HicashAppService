package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;
/**
 * 
* @author dong.liu 
* 2017-8-28 下午5:37:19
* 类说明 :修改客户真实姓名
 */
public class UpdateRelaNameReq extends RequestSequence{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//用户名
	private String userName;
	//真实姓名
	private String realName;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	
	
	
}
