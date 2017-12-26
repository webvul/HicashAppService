package com.hengyuan.hicash.parameters.response.activity;

public class NewYearActivityResp {

	/* 活动1返回参数 */

	/** 加密代码 */
	private String actCode;

	/** 随机数字 */
	private String randomNum;

	/* 活动2返回参数  2016-02-01*/

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

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

}
