package com.hengyuan.hicash.parameters.response.activity;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 新年抽奖活动 resp
 * 
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotteryResp extends ParmResponse {

	/** 加密代码 */
	private String actCode;

	/** 随机数字 */
	private String randomNum;

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
