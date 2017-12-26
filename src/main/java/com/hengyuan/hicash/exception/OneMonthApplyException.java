package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 一个月内不能再次申请
 * @author think
 *
 */
public class OneMonthApplyException extends Exception {

	private static final long serialVersionUID = 1L;

	public OneMonthApplyException(){
		super(ExceptionMsg.ONEMON_CANTNOTAPPLY_EXCEPTION);
	}
	
}
