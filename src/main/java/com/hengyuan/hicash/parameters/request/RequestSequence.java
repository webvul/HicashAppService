package com.hengyuan.hicash.parameters.request;

import com.google.gson.Gson;


/**
 * 请求唯一序列
 * @author Andy.Niu
 * @create 2014-08-01
 */
public class RequestSequence implements ParmRequest{

	
	private static final long serialVersionUID = 3931162086397145872L;
	
	/** 请求唯一序列ID */
	protected String uuid;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	
	
	
	

}
