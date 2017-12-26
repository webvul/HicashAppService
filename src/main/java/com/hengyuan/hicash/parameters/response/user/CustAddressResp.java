package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.CustReceiveAddressEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;
/**
 * 收货地址返回
 * @author ding
 *
 */
public class CustAddressResp extends ParmResponse{
	
	private List<CustReceiveAddressEntity> list;
	
	private CustReceiveAddressEntity custReceiveAddressEntity;
	
	//是否可以添加地址，（第一次还款50%）
	private String isAdd;

	public List<CustReceiveAddressEntity> getList() {
		return list;
	}

	public void setList(List<CustReceiveAddressEntity> list) {
		this.list = list;
	}

	public CustReceiveAddressEntity getCustReceiveAddressEntity() {
		return custReceiveAddressEntity;
	}

	public void setCustReceiveAddressEntity(
			CustReceiveAddressEntity custReceiveAddressEntity) {
		this.custReceiveAddressEntity = custReceiveAddressEntity;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}
	
	
	

}
