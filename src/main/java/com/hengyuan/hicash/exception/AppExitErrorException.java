package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建INPUT PAY异常
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class AppExitErrorException extends Exception{

	private static final long serialVersionUID = -2191392472957260035L;
	
	public AppExitErrorException(){
		super(ExceptionMsg.APP_EXIT_ERROR);
	}

}
