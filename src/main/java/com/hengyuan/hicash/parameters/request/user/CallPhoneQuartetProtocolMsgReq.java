package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 	电信签订四方协议展示，姓名，申请产品名字，每月还款日，每月还款：元期：请求参数类
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPhoneQuartetProtocolMsgReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 订单好 */
	private String appNo;

	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}

	/**
	 * @param appNo the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	

}
