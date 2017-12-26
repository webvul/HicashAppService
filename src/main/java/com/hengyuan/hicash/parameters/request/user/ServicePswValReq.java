package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 验证用户服务密码 req
 * 
 * @author Cary.Liu
 * @createDate 2105-06-09
 *
 */
public class ServicePswValReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 申请单号 */
	private String appNo;

	/** 临时申请单号 */
	private String tempAppNo;

	/** 服务密码 */
	private String servicePsw;

	/** 动态密码 */
	private String dynamicPsw;

	/** 授权标志 */
	private String token;

	/** 提交类型 */
	private String submitType;

	/** 运营商代码 */
	private String webSite;

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getDynamicPsw() {
		return dynamicPsw;
	}

	public void setDynamicPsw(String dynamicPsw) {
		this.dynamicPsw = dynamicPsw;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSubmitType() {
		return submitType;
	}

	public void setSubmitType(String submitType) {
		this.submitType = submitType;
	}

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getServicePsw() {
		return servicePsw;
	}

	public void setServicePsw(String servicePsw) {
		this.servicePsw = servicePsw;
	}

}
