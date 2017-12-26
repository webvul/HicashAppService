package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 活动时间还未开始
 * @author think
 *
 */
public class ActivityNoStartException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public ActivityNoStartException(){
		super(ExceptionMsg.ACT_NOSTART_EXCEPTION);
	}
	

}
