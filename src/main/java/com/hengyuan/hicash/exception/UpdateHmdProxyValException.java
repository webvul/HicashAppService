package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


public class UpdateHmdProxyValException extends Exception{

	private static final long serialVersionUID = -2191392472957260035L;
	
	public UpdateHmdProxyValException(){
		super(ExceptionMsg.APP_EXIT_ERROR);
	}

}
