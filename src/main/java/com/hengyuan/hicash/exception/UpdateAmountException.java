package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class UpdateAmountException extends Exception {

	private static final long serialVersionUID = -9030842356571803391L;
	
	public UpdateAmountException(){
		super(ExceptionMsg.UPDATE_AMOUNT_VALIDATE_EXCEPTION);
	}
	
}
