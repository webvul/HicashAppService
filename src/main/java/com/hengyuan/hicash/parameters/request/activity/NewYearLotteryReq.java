package com.hengyuan.hicash.parameters.request.activity;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 新年抽奖活动 req
 * 
 * @author Cary.Liu
 * @createDate 2015-12-29
 *
 */
public class NewYearLotteryReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 活动类型（申请嗨秒贷抽奖 MDCJ/分享抽奖 FXCJ） */
	private String activityType;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType;
	}

}
