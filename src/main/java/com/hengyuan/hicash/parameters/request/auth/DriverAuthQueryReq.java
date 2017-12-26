package com.hengyuan.hicash.parameters.request.auth;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 司机贷-车主认证查询 请求参数
 * @author yangkun
 *
 */
public class DriverAuthQueryReq extends RequestSequence{

	private static final long serialVersionUID = 1L;
	
	/**行业代码**/
	private String industryCode;
	
	private String username;
	
	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
