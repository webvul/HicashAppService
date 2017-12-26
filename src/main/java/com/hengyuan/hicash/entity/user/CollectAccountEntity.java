package com.hengyuan.hicash.entity.user;

/**
 * 收款账户信息查询实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 * 
 */
public class CollectAccountEntity {

	/** 主键 */
	private String cardId;

	/** 真实姓名 */
	private String realName;

	/** 账户类型 */
	private String cardType;

	/** 开户城市-省 */
	private String province;

	/** 开户城市-市 */
	private String city;

	/** 开户城市-区 */
	private String area;

	/** 开户支行 */
	private String openBank;

	/** 开户支行名称 2015-03-03新增 */
	private String openBankName;

	/** 开户行 */
	private String bank;

	/** 开户行名称 */
	private String bankName;

	/** 收款账号 */
	private String cardNum;

	/** 是否是默认银行卡 */
	private String defaultCard;

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getDefaultCard() {
		return defaultCard;
	}

	public void setDefaultCard(String defaultCard) {
		this.defaultCard = defaultCard;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

}
