package com.hengyuan.hicash.entity.param;

/**
 * 市
 * 
 * @author Cary.Liu
 * 
 */
public class CityEntity {

	/** 城市代码 */
	private String cityCode;

	/** 城市名称 */
	private String cityName;

	private String fatherCode;

	public String getFatherCode() {
		return fatherCode;
	}

	public void setFatherCode(String fatherCode) {
		this.fatherCode = fatherCode;
	}

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
