package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 该次申请不在一个月内
 * @author think
 *
 */
public class ApplyNotInOneMonthException extends Exception {

	private static final long serialVersionUID = 1L;

	public ApplyNotInOneMonthException(){
		super(ExceptionMsg.APPLY_NOTINONEMONTH_EXCEPTION);
	}
	
}
