package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * 电信专案,保存手机套餐返回参数
 * @author LiHua.Ren
 * @createDate 2015-08-18
 */
public class CallPhoneDataNumUpdateResp  extends ParmResponse {

	/** 信贷商品订单信息 */
	private String appFlowNo;

	/**
	 * @return the appFlowNo
	 */
	public String getAppFlowNo() {
		return appFlowNo;
	}

	/**
	 * @param appFlowNo the appFlowNo to set
	 */
	public void setAppFlowNo(String appFlowNo) {
		this.appFlowNo = appFlowNo;
	}


}
