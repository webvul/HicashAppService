package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 根据城市code查询商户
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SupplierInfoByCityMsgReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	private String cityCode;
	/**
	 * @return the cityCode
	 */
	public String getCityCode() {
		return cityCode;
	}
	/**
	 * @param cityCode the cityCode to set
	 */
	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
	
}
