package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;



/**
 * 未找到专案号
 * @author Cary.Liu
 *
 */
public class TaskNoNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public TaskNoNotFoundException(){
		super(ExceptionMsg.ONEMON_TASKNO_NOTFOUND_EXCEPTION);
	}
	
}
