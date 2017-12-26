package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 根据商户id查询售点
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SaleSiteBySupplierMsgReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	private String supplierId;
	/**
	 * @return the supplierId
	 */
	public String getSupplierId() {
		return supplierId;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}
	
	
}
