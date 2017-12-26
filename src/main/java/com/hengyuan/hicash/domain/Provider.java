package com.hengyuan.hicash.domain;


public interface Provider {

	/**
	 * 活动
	 * @param lotteryType 活动类型 1：新年大转盘抽奖活动、2：新年红包抽奖活动
	 * @return
	 */
	public Activity createActivity(int lotteryType);
	
}
