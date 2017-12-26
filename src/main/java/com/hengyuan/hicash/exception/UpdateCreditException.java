package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 
 * 更新资信信息异常
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class UpdateCreditException extends Exception{

	/** */
	private static final long serialVersionUID = -7919807247644489983L;

	public UpdateCreditException(){
		
		super(ExceptionMsg.UPDATE_CREDIT_EXCEPTION);
		
	}
	
	
	
}
