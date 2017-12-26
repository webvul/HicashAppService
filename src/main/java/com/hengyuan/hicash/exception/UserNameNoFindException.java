package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class UserNameNoFindException extends Exception {

	private static final long serialVersionUID = -6893399858438509808L;

	public UserNameNoFindException() {
		super(ExceptionMsg.USER_NOT_FOUND);
	}
	
}
