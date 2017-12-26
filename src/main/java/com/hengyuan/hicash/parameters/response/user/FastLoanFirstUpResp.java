package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 秒贷保存期数金额
 * 
 * @author LiHua.Ren
 * @create date 2015-05-25
 */
public class FastLoanFirstUpResp extends ParmResponse {


	/** 信贷商品订单信息 */
	private String appFlowNo;

	/**
	 * @return the appFlowNo
	 */
	public String getAppFlowNo() {
		return appFlowNo;
	}

	/**
	 * @param appFlowNo
	 *            the appFlowNo to set
	 */
	public void setAppFlowNo(String appFlowNo) {
		this.appFlowNo = appFlowNo;
	}


}
