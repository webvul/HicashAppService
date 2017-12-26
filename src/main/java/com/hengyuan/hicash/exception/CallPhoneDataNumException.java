package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * @author 电信专案手机号码未找到
 *
 */
public class CallPhoneDataNumException extends Exception{

	private static final long serialVersionUID = -3453520519564844687L;
	
	public CallPhoneDataNumException(){
		super(ExceptionMsg.CALL_PHONE_NOTFOUND);
	}

}
