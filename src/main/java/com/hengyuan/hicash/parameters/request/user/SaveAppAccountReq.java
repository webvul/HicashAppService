package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 保存申请件账户资料 req 提交申请（蓝领数码业务）
 * 
 * @author Cary.Liu
 * @createDate 2016-01-26
 *
 */
public class SaveAppAccountReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 申请单号 */
	private String appNo;

	/** 开户行 */
	private String openBank;

	/** 银行卡号 */
	private String bankCard;

	/** 是否开通代扣功能 */
	private String isOpenProxy;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getOpenBank() {
		return openBank;
	}

	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getIsOpenProxy() {
		return isOpenProxy;
	}

	public void setIsOpenProxy(String isOpenProxy) {
		this.isOpenProxy = isOpenProxy;
	}

}
