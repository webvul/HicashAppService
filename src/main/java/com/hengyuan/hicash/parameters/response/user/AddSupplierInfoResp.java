package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/** 
 * 商户入驻-新增商户 resp
 * @author Cary.Liu
 * @createDate 2015-07-10
 * 
 */
public class AddSupplierInfoResp extends ParmResponse {
	
	/** 商户Id */
	private String supplierId;

	/** 商户名称 */
	private String supplierName;

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	
	
}
