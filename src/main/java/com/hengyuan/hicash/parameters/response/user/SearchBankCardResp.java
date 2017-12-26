package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 查询银行卡信息 返回参数
 * 
 * @author Cary.Liu
 * @create 2014-09-29
 * 
 */
public class SearchBankCardResp extends ParmResponse {

	/** 主键 */
	private String cardId;

	/** 真实姓名 */
	private String realName;

	/** 账户类型 */
	private String cardType;

	/** 开户城市-省 */
	private String provinceName;

	/** 开户城市-省名称 */
	private String province;

	/** 开户城市-市 */
	private String city;

	/** 开户城市-市名称 */
	private String cityName;

	/** 开户城市-区 */
	private String area;

	/** 开户城市-区名称 */
	private String areaName;

	/** 开户支行 */
	private String openBank;

	/** 开户支行名称 */
	private String openBankName;

	/** 开户行 */
	private String bank;

	/** 开户行名称 */
	private String bankName;

	/** 收款账号 */
	private String cardNum;

	/** 是否是默认银行卡 */
	private String defaultCard;
	
	private String bankNo;//唯一代码
	
	private String signUrl;//
	
	
	/** 返回代码 */
	/*private String resultCode;*/
	
	/**银行卡基本信息*/
	private List<SearchBankCardListResp> list;
	

	public List<SearchBankCardListResp> getList() {
		return list;
	}

	public void setList(List<SearchBankCardListResp> list) {
		this.list = list;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getOpenBankName() {
		return openBankName;
	}

	public void setOpenBankName(String openBankName) {
		this.openBankName = openBankName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCardId() {
		return cardId;
	}

	public void setCardId(String cardId) {
		this.cardId = cardId;
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

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

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

	public String getDefaultCard() {
		return defaultCard;
	}

	public void setDefaultCard(String defaultCard) {
		this.defaultCard = defaultCard;
	}

	public String getSignUrl() {
		return signUrl;
	}

	public void setSignUrl(String signUrl) {
		this.signUrl = signUrl;
	}
	

}
