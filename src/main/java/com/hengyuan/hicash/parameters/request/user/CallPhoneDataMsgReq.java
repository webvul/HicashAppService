package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 	根据城市ID获取电信套餐内容：请求参数类
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneDataMsgReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 城市ID */
	private String cityCode;

	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}

	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	


}
