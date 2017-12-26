package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class SaveMyMsgReq extends RequestSequence{
	
	private static final long serialVersionUID = 1L;
	
	/** 行业代码*/
	private String industryCode;
	
	/** 用户名*/
	private String username;

	/** 结果 */
	private String status;

	/** 消息Code */
	private String code;
	
	/** 流水号 */
	private String appNo;
	
	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
