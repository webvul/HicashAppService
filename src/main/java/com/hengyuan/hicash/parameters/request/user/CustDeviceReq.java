package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;


/**
 * 
* @author dong.liu 
* 2017-4-7 下午5:50:02
* 类说明:保存设备信息
 */
public class CustDeviceReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String username;

	/** 流水号 */
	private String app_no;

	/** 类型:TX/提现,SX/授信*/
	private String type;

	/** 设备系统 */
	private String sb_system;

	/** 设备型号 */
	private String sb_type;

	/** 唯一标示 */
	private String udid;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getApp_no() {
		return app_no;
	}

	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSb_system() {
		return sb_system;
	}

	public void setSb_system(String sb_system) {
		this.sb_system = sb_system;
	}

	public String getSb_type() {
		return sb_type;
	}

	public void setSb_type(String sb_type) {
		this.sb_type = sb_type;
	}

	public String getUdid() {
		return udid;
	}

	public void setUdid(String udid) {
		this.udid = udid;
	}

	

	
}
