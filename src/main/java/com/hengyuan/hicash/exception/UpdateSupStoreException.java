package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class UpdateSupStoreException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateSupStoreException(){
		super(ExceptionMsg.UPDATE_SUPPSTORE_EXCEPTION);
	}
	
}
