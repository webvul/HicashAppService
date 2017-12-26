package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 嗨秒贷 resp
 * 验证用户收款银行卡信息
 * 代理扣款
 * @author lihua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyNewResp extends ParmResponse {
	
	/** 验证码状态 */
	private String valStatus;
	/** 订单号 */
	private String merOrderId;
	/** 客户号 */
	private String custId;
	/** 令牌信息 */
	private String phoneToken;
	/**流水号，第二步更改验证表状态的时候用*/
	private String orgBussFlowNo;
	/**
	 * @return the merOrderId
	 */
	public String getMerOrderId() {
		return merOrderId;
	}
	/**
	 * @param merOrderId the merOrderId to set
	 */
	public void setMerOrderId(String merOrderId) {
		this.merOrderId = merOrderId;
	}
	/**
	 * @return the custId
	 */
	public String getCustId() {
		return custId;
	}
	/**
	 * @param custId the custId to set
	 */
	public void setCustId(String custId) {
		this.custId = custId;
	}
	/**
	 * @return the phoneToken
	 */
	public String getPhoneToken() {
		return phoneToken;
	}
	/**
	 * @param phoneToken the phoneToken to set
	 */
	public void setPhoneToken(String phoneToken) {
		this.phoneToken = phoneToken;
	}
	/**
	 * @return the orgBussFlowNo
	 */
	public String getOrgBussFlowNo() {
		return orgBussFlowNo;
	}
	/**
	 * @param orgBussFlowNo the orgBussFlowNo to set
	 */
	public void setOrgBussFlowNo(String orgBussFlowNo) {
		this.orgBussFlowNo = orgBussFlowNo;
	}
	/**
	 * @return the valStatus
	 */
	public String getValStatus() {
		return valStatus;
	}
	/**
	 * @param valStatus the valStatus to set
	 */
	public void setValStatus(String valStatus) {
		this.valStatus = valStatus;
	}
	
	
}
