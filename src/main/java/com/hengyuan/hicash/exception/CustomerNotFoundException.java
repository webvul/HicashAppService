package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


/**
 * Customer未查询到
 * @author Andy.Niu
 * @create 2014-08-06
 */
public class CustomerNotFoundException extends Exception{

	private static final long serialVersionUID = -3453520519564844687L;
	
	public CustomerNotFoundException(){
		super(ExceptionMsg.CUST_NOT_FOUND);
	}
	
	

}
