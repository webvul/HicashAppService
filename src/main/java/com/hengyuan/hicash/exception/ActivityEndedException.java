package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 活动时间已经结束
 * @author think
 *
 */
public class ActivityEndedException extends Exception {

	private static final long serialVersionUID = -5252667584428149667L;

	public ActivityEndedException(){
		super(ExceptionMsg.ACT_ENDED_EXCEPTION);
	}
	
}
