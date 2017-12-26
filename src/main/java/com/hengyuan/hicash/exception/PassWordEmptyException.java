package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class PassWordEmptyException extends Exception {

	private static final long serialVersionUID = -6893399858438509808L;

	public PassWordEmptyException() {
		super(ExceptionMsg.PASSWORD_IS_NULL);
	}
	
}
