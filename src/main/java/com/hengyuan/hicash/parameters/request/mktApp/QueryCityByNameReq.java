package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 根据名称获取城市 req
 * 
 * @author Cary.Liu
 * @createDate 2015-05-19
 *
 */
public class QueryCityByNameReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 城市名称 */
	private String cityName;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
