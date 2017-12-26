package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 已经抽过一次
 * @author think
 *
 */
public class ActivityIsLotteryException extends Exception {

	private static final long serialVersionUID = 1756625387411343124L;

	public ActivityIsLotteryException(){
		super(ExceptionMsg.ACT_ISLOTTERY_EXCEPTION);
	}
	
}
