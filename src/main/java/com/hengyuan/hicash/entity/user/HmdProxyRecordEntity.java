package com.hengyuan.hicash.entity.user;

/**
 * 嗨秒贷代扣记录
 * 
 * @author Cary.Liu
 *
 */
public class HmdProxyRecordEntity {

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 身份证号码 */
	private String identityNo;

	/** 银行卡号 */
	private String bankCard;

	/** 银行名称 */
	private String bankName;

	/** 流水号 */
	private String bussFlowNo;

	/** 代扣状态 */
	private String proxyStatus;

	/**  */
	private String createDate;

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

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBussFlowNo() {
		return bussFlowNo;
	}

	public void setBussFlowNo(String bussFlowNo) {
		this.bussFlowNo = bussFlowNo;
	}

	public String getProxyStatus() {
		return proxyStatus;
	}

	public void setProxyStatus(String proxyStatus) {
		this.proxyStatus = proxyStatus;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
