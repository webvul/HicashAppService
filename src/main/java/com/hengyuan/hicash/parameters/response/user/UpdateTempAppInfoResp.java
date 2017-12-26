package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/** 
 * @author dong.liu 
 * 2017-3-2 下午2:25:10
 * 类说明 
 */
public class UpdateTempAppInfoResp extends ParmResponse{
	
	
	
	/**
	 * 申请件流程状态
	 */
	private String validateType;
	
	/**
	 * 申请件流程状态副本
	 */
	private String validateTypea;

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

	public String getValidateTypea() {
		return validateTypea;
	}

	public void setValidateTypea(String validateTypea) {
		this.validateTypea = validateTypea;
	}
	
	
	

}
