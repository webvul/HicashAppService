package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * @author Administrator
 *
 */
public class FastLoanFirstMsgResp extends ParmResponse{

	/** 登录用户名 */
	private String userName;
	/** 秒贷金额 */
	private String fastAmt;
	/** 秒贷期数 ,对应的是产品id*/
	private String fastPeriod;
	//业务流水号
	private String tempAppNo;
	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param userName the userName to set
	 */
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
	/**
	 * @return the tempAppNo
	 */
	public String getTempAppNo() {
		return tempAppNo;
	}
	/**
	 * @param tempAppNo the tempAppNo to set
	 */
	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}
	

}
