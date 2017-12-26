package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 临时申请列表 请求参数
 * 
 * @author Cary.Liu
 * @create 2015-03-12
 *
 */
public class PaymentReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

    private String cityCode;
    
    private String custType;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}
    
    
    
 
}
