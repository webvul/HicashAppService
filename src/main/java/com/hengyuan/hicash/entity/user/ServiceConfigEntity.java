package com.hengyuan.hicash.entity.user;
/**
 * 服务代用接口地址实体类
 * 
 * @author laughing.peng
 * @create date 2014-07-22
 */
public class ServiceConfigEntity {
	
	/** 服务调用地址**/
    private String serviceUrl;
    
    /** 服务名称**/
    private String serviceName;
    
    /** 服务代码**/
    private String serviceCode;

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
    
    
    
}
