package com.hengyuan.hicash.entity.user;

/**
 * 远程调用服务返回码对象
 * 
 * @author Administrator
 * 
 */
public class RemotEntity {

	/** 返回码 */
	private String resultCode;

	private String uuid;

	private String resultMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

}
