package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 查询申请件的代扣银行卡
 * 
 * @author lihua.Ren
 * @create date 2015-12-03
 *
 */
public class WithHoldingBankCardReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String appNo;

	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}

	/**
	 * @param appNo
	 *            the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

}
