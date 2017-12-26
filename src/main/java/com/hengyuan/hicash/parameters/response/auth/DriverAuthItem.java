package com.hengyuan.hicash.parameters.response.auth;

/**
 * 司机认证 项
 * @author yangkun
 *
 */
public class DriverAuthItem {

	/**图标url**/
	private String icoUrl;
	
	private String name;
	/**类别  1-滴滴**/
	private String type;
	/**平台账号**/
	private String driverUsername;
	
	
	public String getIcoUrl() {
		return icoUrl;
	}
	public void setIcoUrl(String icoUrl) {
		this.icoUrl = icoUrl;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getDriverUsername() {
		return driverUsername;
	}
	public void setDriverUsername(String driverUsername) {
		this.driverUsername = driverUsername;
	}
	
}
