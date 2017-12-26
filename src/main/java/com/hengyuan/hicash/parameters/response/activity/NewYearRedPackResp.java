package com.hengyuan.hicash.parameters.response.activity;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 新年红包抽取 resp
 * 
 * @author Cary.Liu
 * @createDate 2016-02-01
 *
 */
public class NewYearRedPackResp extends ParmResponse {

	/** 是否中奖 1：是、0：否 */
	private String isLottery;

	/** 奖品名称 */
	private String lotteryName;

	/** 未中奖的提示消息 */
	private String rtnNoLotteryMsg;

	public String getIsLottery() {
		return isLottery;
	}

	public void setIsLottery(String isLottery) {
		this.isLottery = isLottery;
	}

	public String getLotteryName() {
		return lotteryName;
	}

	public void setLotteryName(String lotteryName) {
		this.lotteryName = lotteryName;
	}

	public String getRtnNoLotteryMsg() {
		return rtnNoLotteryMsg;
	}

	public void setRtnNoLotteryMsg(String rtnNoLotteryMsg) {
		this.rtnNoLotteryMsg = rtnNoLotteryMsg;
	}

}
