package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新CUST_USER失败
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class UpdateCustUserException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7199238162725072062L;
	
	public UpdateCustUserException(){
		super(ExceptionMsg.UPDATE_CUST_USER_EXCEPTION);
	}

}
