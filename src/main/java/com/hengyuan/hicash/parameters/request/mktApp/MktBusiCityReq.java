package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 二次营销-业务开放城市 请求参数
 * 
 * @author Cary.Liu
 * @create 2015-03-13
 *
 */
public class MktBusiCityReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 客户类型 */
	private String custType;

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

}
