package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.parameters.response.ParmResponse;

public class DkInfoByAppNoMsgResp extends ParmResponse {
	
	private List<InputAppPay> dkAppList;

	public List<InputAppPay> getDkAppList() {
		return dkAppList;
	}

	public void setDkAppList(List<InputAppPay> dkAppList) {
		this.dkAppList = dkAppList;
	}
	
	

}
