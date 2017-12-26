package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class SaveCustomerException extends Exception{

	
	private static final long serialVersionUID = -2330730110310964379L;
	
	public SaveCustomerException(){
		super(ExceptionMsg.CREATE_CUSTOMER_EXCEPTION);
	}

}
