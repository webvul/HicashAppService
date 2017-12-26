package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 记录收款账户信息 请求参数
 * 
 * @author Cary.Liu
 * @create date 2014-07-25
 */
public class CollectCardReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 卡类型 */
	private String cardType;

	/** 开户行 */
	private String bank;

	/** 开户城市-省 */
	private String province;

	/** 开户城市-市 */
	private String city;

	/** 开户支行 */
	private String openBank;

	/** 支行名称 */
	private String openBankName;

	/** 收款账号 */
	private String cardNum;

	/** 确认账号 */
	private String confrimCardNum;

	/** 新增还是修改 1为修改 默认为新增 */
	private String saveOrUpdateFlag;

	/** 是否设置为默认卡 (Y:设置[默认] N:不设置) */
	private String defaultCard;

	/** 银行网点所在区域代码 2015-03-03 新增 */
	private String areaCode;
	
	/** 预留手机号 */
	private String mobile;

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
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

	public String getConfrimCardNum() {
		return confrimCardNum;
	}

	public void setConfrimCardNum(String confrimCardNum) {
		this.confrimCardNum = confrimCardNum;
	}

	public String getSaveOrUpdateFlag() {
		return saveOrUpdateFlag;
	}

	public void setSaveOrUpdateFlag(String saveOrUpdateFlag) {
		this.saveOrUpdateFlag = saveOrUpdateFlag;
	}

	public String getDefaultCard() {
		return defaultCard;
	}

	public void setDefaultCard(String defaultCard) {
		this.defaultCard = defaultCard;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
