package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class UserNameEmptyException extends Exception {

	private static final long serialVersionUID = -6893399858438509808L;

	public UserNameEmptyException() {
		super(ExceptionMsg.USERNAME_IS_NULL);
	}
	
}
