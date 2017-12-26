package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class PassWordNotEqualException extends Exception {

	private static final long serialVersionUID = -6893399858438509808L;

	public PassWordNotEqualException() {
		super(ExceptionMsg.CHANGE_PASSORD_NOT_EQUAL);
	}
	
}
