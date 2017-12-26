package com.hengyuan.hicash.entity.user;

/**
 * 嗨秒贷代扣验证结果
 * 
 * @author Cary.Liu
 *
 */
public class HmdProxyValEntity {

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

	/** 验证状态 */
	private String status;

	/** 验证结果 */
	private String valMsg;

	/** 流水号 */
	private String bussFlowNo;

	/** 验证验证码流水号 */
	private String bussFlowNoVal ;

	/** 代扣状态 */
	private String proxyStatus;

	/** 代扣时间 */
	private String proxyTime;

	/**  */
	private String createDate;
	
	/** 订单号 */
	private String merOrderId;
	/** 客户号 */
	private String custId;
	/** 令牌信息 */
	private String phoneToken;

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

	public String getProxyTime() {
		return proxyTime;
	}

	public void setProxyTime(String proxyTime) {
		this.proxyTime = proxyTime;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getValMsg() {
		return valMsg;
	}

	public void setValMsg(String valMsg) {
		this.valMsg = valMsg;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the bussFlowNoVal
	 */
	public String getBussFlowNoVal() {
		return bussFlowNoVal;
	}

	/**
	 * @param bussFlowNoVal the bussFlowNoVal to set
	 */
	public void setBussFlowNoVal(String bussFlowNoVal) {
		this.bussFlowNoVal = bussFlowNoVal;
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

}
