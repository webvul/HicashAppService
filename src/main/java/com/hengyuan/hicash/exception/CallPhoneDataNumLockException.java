package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * @author Administrator
 *
 */
public class CallPhoneDataNumLockException  extends Exception{

	private static final long serialVersionUID = -3453520519564844687L;
	
	public CallPhoneDataNumLockException(){
		super(ExceptionMsg.CALL_PHONE_DATA_LOCK);
	}

}
