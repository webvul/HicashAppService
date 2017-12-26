package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 删除银行卡异常
 * 
 * @author Cary.Liu
 * @create 2014-08-15
 * 
 */
public class RemoveCardException extends Exception {

	private static final long serialVersionUID = -7006837888684086703L;

	public RemoveCardException() {
		super(ExceptionMsg.REMOVE_CARD_EXCEPTION);
	}

}
