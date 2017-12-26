package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


public class CmbcIdentifyException extends Exception{
	
	private static final long serialVersionUID = -6510325879462592858L;
	
	
	public CmbcIdentifyException(){
		super(ExceptionMsg.CMBC_IDENTIFY_EXCEPTION);
	}
	
	

}
