package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class PassWordEqualException extends Exception {

	private static final long serialVersionUID = -6893399858438509808L;

	public PassWordEqualException() {
		super(ExceptionMsg.PASSWORD_EQUALS);
	}
	
}
