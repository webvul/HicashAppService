package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * @author Administrator
 *
 */
public class ProxyDeductMoneyNewReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 银行卡号 */
	private String bankCard;

	/** 开户行代码 */
	private String openBank;

//	/** 扣款金额 */
//	private String amount;
	
	/** 扣款手机号码 */
	private String mobileNo;

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
	 * @return the bankCard
	 */
	public String getBankCard() {
		return bankCard;
	}

	/**
	 * @param bankCard the bankCard to set
	 */
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	/**
	 * @return the openBank
	 */
	public String getOpenBank() {
		return openBank;
	}

	/**
	 * @param openBank the openBank to set
	 */
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

//	/**
//	 * @return the amount
//	 */
//	public String getAmount() {
//		return amount;
//	}
//
//	/**
//	 * @param amount the amount to set
//	 */
//	public void setAmount(String amount) {
//		this.amount = amount;
//	}

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
	
}
