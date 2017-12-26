package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 用户注册抽奖 resp
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterLotteryResp extends ParmResponse {

	/** 抽奖金额 */
	private String lotteryAmount;

	public String getLotteryAmount() {
		return lotteryAmount;
	}

	public void setLotteryAmount(String lotteryAmount) {
		this.lotteryAmount = lotteryAmount;
	}

}
