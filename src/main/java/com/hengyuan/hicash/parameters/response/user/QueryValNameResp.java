package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;


/**
 * 
* @author dong.liu 
* 2017-9-5 下午6:17:47
* 类说明
 */
public class QueryValNameResp extends ParmResponse {

	
	private String realName;
	
	private String isUpdateName;

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIsUpdateName() {
		return isUpdateName;
	}

	public void setIsUpdateName(String isUpdateName) {
		this.isUpdateName = isUpdateName;
	}

	
	
	

}
