package com.hengyuan.hicash.parameters.response.auth;

import java.util.List;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 司机贷-车主认证查询
 * @author yangkun
 *
 */
public class DriverAuthQueryResp extends ParmResponse{
	
	
	private List<DriverAuthItem> driverAuthItems;

	public List<DriverAuthItem> getDriverAuthItems() {
		return driverAuthItems;
	}

	public void setDriverAuthItems(List<DriverAuthItem> driverAuthItems) {
		this.driverAuthItems = driverAuthItems;
	}
	
	
}
