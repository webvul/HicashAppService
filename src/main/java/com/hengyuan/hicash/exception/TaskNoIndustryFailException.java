package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;



public class TaskNoIndustryFailException extends Exception {

	private static final long serialVersionUID = 1L;

	public TaskNoIndustryFailException(){
		super(ExceptionMsg.ONEMON_TASKNO_FAIL_EXCEPTION);
	}
	
}
