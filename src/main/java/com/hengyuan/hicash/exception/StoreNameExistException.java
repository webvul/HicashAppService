package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class StoreNameExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public StoreNameExistException(){
		super(ExceptionMsg.STORENAME_EXIST_EXCEPTION);
	}
	
}
