package com.hengyuan.hicash.parameters.response;

import com.google.gson.Gson;

/**
 * 接口返回公共参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-20
 *
 */
public abstract class ParmResponse implements ParmResp {

	/** 请求唯一序列ID */
	protected String uuid;

	/** 返回代码 */
	private String resultCode;

	/** 返回消息 */
	private String resultMsg;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMsg() {
		return resultMsg;
	}

	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

}
