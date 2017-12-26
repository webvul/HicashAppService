package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 非蓝领用户
 * @author think
 *
 */
public class IsNotLanUserException extends Exception {

	private static final long serialVersionUID = 1L;

	public IsNotLanUserException(){
		super(ExceptionMsg.APPLY_ISNOTLANUSER_EXCEPTION);
	}
	
}
