package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class NotInActivityTimeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotInActivityTimeException() {
		super(ExceptionMsg.NOT_IN_ACTIVITY_TIME_EXCEPTION);
	}

}
