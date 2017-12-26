package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 忘记密码响应类
 * 
 * @author Eric
 * @create date 2014-07-24
 * 
 */
public class ForgetPassWordResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	/** 返回用户名 */
	private String userName;

	/** 返回加密串 */
	private String mobileAndCode;

	/** 登录认证 */
	private String token;

	/** 页面索引 */
	private String pageIndex;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileAndCode() {
		return mobileAndCode;
	}

	public void setMobileAndCode(String mobileAndCode) {
		this.mobileAndCode = mobileAndCode;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(String pageIndex) {
		this.pageIndex = pageIndex;
	}

}
