package com.hengyuan.hicash.entity.mer;

/**
 * 售点
 * 
 * @author think
 *
 */
public class SaleSiteEntity {

	/** 售点ID */
	private String siteId;

	private String siteName;

	private String logicCode;

	/** 所属商户 */
	private String defaultSupplier;

	/** 所属商户逻辑编码 */
	private String supLogicCode;

	private String province;

	private String city;

	private String area;

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getLogicCode() {
		return logicCode;
	}

	public void setLogicCode(String logicCode) {
		this.logicCode = logicCode;
	}

	public String getDefaultSupplier() {
		return defaultSupplier;
	}

	public void setDefaultSupplier(String defaultSupplier) {
		this.defaultSupplier = defaultSupplier;
	}

	public String getSupLogicCode() {
		return supLogicCode;
	}

	public void setSupLogicCode(String supLogicCode) {
		this.supLogicCode = supLogicCode;
	}

}
