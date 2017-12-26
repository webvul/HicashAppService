package com.hengyuan.hicash.entity;

/**
 * 登陆用户查询实体
 * @author Mary.luo
 * @create date 2014-08-11
 */
public class ServiceConvertEntity {
	
	private String id;

	private String serviceIp;
	
	private String servicePort;
	
	private String serviceUrl;
	
	private String encoding;
	
	private String limitIp;
	
	
	
	
	



	public String getServiceIp() {
		return serviceIp;
	}

	public void setServiceIp(String serviceIp) {
		this.serviceIp = serviceIp;
	}

	public String getServicePort() {
		return servicePort;
	}

	public void setServicePort(String servicePort) {
		this.servicePort = servicePort;
	}

	

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public String getLimitIp() {
		return limitIp;
	}

	public void setLimitIp(String limitIp) {
		this.limitIp = limitIp;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	

}
