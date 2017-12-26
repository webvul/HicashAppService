package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class UpdateLotteryException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateLotteryException(){
		super(ExceptionMsg.UPDATE_LOTTERY_EXCEPTION);
	}
	
}
