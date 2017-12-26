package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * @author Administrator
 *
 */
public class BankCardInputAppReq extends RequestSequence {
	private static final long serialVersionUID = -546865098483075661L;
	private String appNo;
	private String bankCard;
	private String openBank;
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

}
