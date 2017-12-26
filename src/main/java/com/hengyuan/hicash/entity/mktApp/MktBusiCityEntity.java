package com.hengyuan.hicash.entity.mktApp;

/**
 * 二次营销业务开放城市
 * 
 * @author Cary.Liu
 *
 */
public class MktBusiCityEntity {

	/** 城市代码 */
	private String cityCode;

	/** 城市名称 */
	private String cityName;

	/** 省份代码 */
	private String provinceCode;

	/** 省份名称 */
	private String provinceName;

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

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

}
