package com.hengyuan.hicash.entity.user;

/**
 * 用户注册抽奖记录
 * 
 * @author Cary.Liu
 *
 */
public class CustRegLotteryEntity {

	private String userName;

	private String amount;

	private String createDate;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}
