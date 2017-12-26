package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 嗨秒贷 req 验证用户收款银行卡信息 代收 快捷充值
 * 
 * @author LiHua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyRechargeReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 银行卡号 */
	private String bankCard;

	/** 开户行代码 */
	private String openBank;

//	/** 扣款金额 */
//	private String amount;
	/** 订单号 */
	private String merOrderId;
	/** 客户号 */
	private String custId;
	/** 令牌信息 */
	private String phoneToken;
	/** 手机验证码 */
	private String phoneVerCode;
	/** 手机号码 */
	private String mobileNo;
	/** 流水号 */
	private String orgBussFlowNo;
	
	/** 订单号 */
	private String appNo;
//	public String getAmount() {
//		return amount;
//	}
//
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	/**
	 * @return the mobileNo
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * @param mobileNo the mobileNo to set
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

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
	 * @return the phoneVerCode
	 */
	public String getPhoneVerCode() {
		return phoneVerCode;
	}

	/**
	 * @param phoneVerCode the phoneVerCode to set
	 */
	public void setPhoneVerCode(String phoneVerCode) {
		this.phoneVerCode = phoneVerCode;
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
