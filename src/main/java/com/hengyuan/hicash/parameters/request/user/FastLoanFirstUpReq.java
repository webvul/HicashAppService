package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 秒贷保存期数金额（保存）
 * 
 * @author LiHua.Ren
 * @create date 2015-05-25
 *
 */
public class FastLoanFirstUpReq extends RequestSequence {

	private static final long serialVersionUID = 1L;
	
	/** 登录用户名 */
	private String userName;
	/** 秒贷金额 */
	private String fastAmt;
	/** 秒贷期数 ,对应的是产品id*/
	private String fastPeriod;
	//业务流水号
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the fastAmt
	 */
	public String getFastAmt() {
		return fastAmt;
	}

	/**
	 * @param fastAmt the fastAmt to set
	 */
	public void setFastAmt(String fastAmt) {
		this.fastAmt = fastAmt;
	}

	/**
	 * @return the fastPeriod
	 */
	public String getFastPeriod() {
		return fastPeriod;
	}

	/**
	 * @param fastPeriod the fastPeriod to set
	 */
	public void setFastPeriod(String fastPeriod) {
		this.fastPeriod = fastPeriod;
	}
	
	

}
