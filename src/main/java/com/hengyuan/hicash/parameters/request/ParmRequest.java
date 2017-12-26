package com.hengyuan.hicash.parameters.request;

import java.io.Serializable;

/**
 * 接收参数总集合
 * 
 * @author Andy.Niu
 *
 */
public interface ParmRequest extends Serializable{
	
	
	/** 请求唯一序列ID */
	public String getUuid();
	
	public String toJson();
}
