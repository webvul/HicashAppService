package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * app首页展示(图片和热卖商品)
 * 
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
public class HomePageInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 城市代码 */
	private String cityCode;

	/** 用户名 */
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
