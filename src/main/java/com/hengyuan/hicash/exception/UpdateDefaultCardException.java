package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新默认银行卡异常
 * 
 * @author Cary.Liu
 * @create 2014-08-12
 */
public class UpdateDefaultCardException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4029908627120776179L;

	public UpdateDefaultCardException() {
		super(ExceptionMsg.UPDATE_DEFAULT_CARD_EXCEPTION);
	}

}
