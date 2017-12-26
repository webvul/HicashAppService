package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/** 
 * @author dong.liu 
 * 2017-1-17 下午6:05:43
 * 类说明 
 */
public class DayException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public DayException(){
		super(ExceptionMsg.DAY_EXCEPTION);
	}

}
