package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 剩余抽奖次数是否大于0
 * @author think
 *
 */
public class ActivityLotteryNumbzException extends Exception {

	private static final long serialVersionUID = 1L;

	public ActivityLotteryNumbzException(){
		super(ExceptionMsg.ACT_LOTTERYNUMBZ_EXCEPTION);
	}
	
}
