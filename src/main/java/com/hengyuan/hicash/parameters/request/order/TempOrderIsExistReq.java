package com.hengyuan.hicash.parameters.request.order;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 临时定时是都存在 请求参数
 * @author yangkun
 *
 */
public class TempOrderIsExistReq extends RequestSequence{

	private static final long serialVersionUID = 1L;
	
	private String username;
	/**行业代码**/
	private String industryCode;
	
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}
}
