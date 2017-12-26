package com.hengyuan.hicash.parameters.request.auth;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 司机贷-车主认证保存 请求参数
 * @author yangkun
 *
 */
public class DriverAuthSaveReq extends RequestSequence{

	private static final long serialVersionUID = 1L;
	
	/**当前的临时订单号**/
	private String tempApplicationNo;
	
	/**类别  1-滴滴**/
	private String type;
	
	/**
	 * 司机账号
	 */
	private String driverUsername;

	public String getTempApplicationNo() {
		return tempApplicationNo;
	}

	public void setTempApplicationNo(String tempApplicationNo) {
		this.tempApplicationNo = tempApplicationNo;
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
