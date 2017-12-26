package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 用户收款账户返回参数
 * 
 * @author Cary.Liu
 * @create date 2014-07-25
 */
public class CollectCardResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;

	/** 卡的唯一Id */
	private String cardId;

	/** 卡类型 */
	private String cardType;

	/** 开户行 */
	private String bank;

	private String bankName;

	/** 银行卡号 */
	private String cardNum;

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

}
