package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 查询申请件的代扣银行卡
 * 
 * @author lihua.Ren
 * @create date 2015-12-03
 *
 */
public class WithHoldingBankCardResp extends ParmResponse {
	/** 银行代码 */
	private String openBank;
	/** 银行卡号 */
	private String bankCard;
	/** 手机好 */
	private String mobileNo;
	/** 姓名 */
	private String realName;
	/** 身份证号 */
	private String identifyNo;
	
	private String valStatus;
	
	private String relaArea;
	
	private String relaCity;
	
	private String relaProv;
	
	private String proxyFrom;
	
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
	 * @return the realName
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * @param realName the realName to set
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * @return the identifyNo
	 */
	public String getIdentifyNo() {
		return identifyNo;
	}
	/**
	 * @param identifyNo the identifyNo to set
	 */
	public void setIdentifyNo(String identifyNo) {
		this.identifyNo = identifyNo;
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
	public String getRelaArea() {
		return relaArea;
	}
	public void setRelaArea(String relaArea) {
		this.relaArea = relaArea;
	}
	public String getRelaCity() {
		return relaCity;
	}
	public void setRelaCity(String relaCity) {
		this.relaCity = relaCity;
	}
	public String getRelaProv() {
		return relaProv;
	}
	public void setRelaProv(String relaProv) {
		this.relaProv = relaProv;
	}
	public String getProxyFrom() {
		return proxyFrom;
	}
	public void setProxyFrom(String proxyFrom) {
		this.proxyFrom = proxyFrom;
	}
    
	
	

}
