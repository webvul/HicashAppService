package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.entity.user.HyAddress;
import com.hengyuan.hicash.parameters.response.ParmResponse;


public class HyAddressResp extends ParmResponse{
	
	private HyAddress hyAddress;

	public HyAddress getHyAddress() {
		return hyAddress;
	}

	public void setHyAddress(HyAddress hyAddress) {
		this.hyAddress = hyAddress;
	}

}
