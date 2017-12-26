package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.mer.SupplierEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 根据城市code查询商户
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SupplierInfoByCityMsgResp extends ParmResponse {

	private List<SupplierEntity> supplierList;

	/**
	 * @return the supplierList
	 */
	public List<SupplierEntity> getSupplierList() {
		return supplierList;
	}

	/**
	 * @param supplierList the supplierList to set
	 */
	public void setSupplierList(List<SupplierEntity> supplierList) {
		this.supplierList = supplierList;
	}


}
