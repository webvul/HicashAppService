package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * 获取账户当前可用余额 resp
 * 
 * @author Cary.Liu
 * @createDate 2015-07-03
 * 
 */
public class AvailBalanceResp extends ParmResponse {

	/** 当前可用余额 */
	private String balance;
	
	/** 近7天待还款总额 */
	private String lateTotal;
	
	/**建议充值金额*/
	private String rechargeAmount;
	/**是否只有有海尔订单且非逾期*/
	private String isHaier;
	/**海尔绑定的银行卡号*/
	private String bankCard;
	/**提示文字*/
	private String sign_str;


	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLateTotal() {
		return lateTotal;
	}

	public void setLateTotal(String lateTotal) {
		this.lateTotal = lateTotal;
	}

	public String getRechargeAmount() {
		return rechargeAmount;
	}

	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}

	public String getIsHaier() {
		return isHaier;
	}

	public void setIsHaier(String isHaier) {
		this.isHaier = isHaier;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getSign_str() {
		return sign_str;
	}

	public void setSign_str(String sign_str) {
		this.sign_str = sign_str;
	}
	
	

}
