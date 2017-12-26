package com.hengyuan.hicash.parameters.response.mktApp;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 根据名称获取城市 resp
 * 
 * @author Cary.Liu
 * @createDate 2015-05-19
 *
 */
public class QueryCityByNameResp extends ParmResponse {

	/** 城市代码 */
	private String cityCode;

	/** 城市名称 */
	private String cityName;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

}
