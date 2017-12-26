package com.hengyuan.hicash.parameters.response.amount;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 还款总额（近七天） resp
 * 
 * @author Cary.Liu
 * @createDate 2015-10-15
 *
 */
public class RepaymentTotalAmountResp extends ParmResponse {

	/** 近7天待还款总额 */
	private String lateTotal;

	public String getLateTotal() {
		return lateTotal;
	}

	public void setLateTotal(String lateTotal) {
		this.lateTotal = lateTotal;
	}

}
