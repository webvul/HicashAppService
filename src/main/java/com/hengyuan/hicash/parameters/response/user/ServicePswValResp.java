package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 验证用户服务密码 resp
 * 
 * @author Cary.Liu
 * @createDate 2105-06-09
 *
 */
public class ServicePswValResp extends ParmResponse {

	private String token;

	private String webSite;

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
