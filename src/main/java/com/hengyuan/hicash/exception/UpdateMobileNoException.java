package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新手机号码失败
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class UpdateMobileNoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3384372899038022616L;

	public UpdateMobileNoException(){
		super(ExceptionMsg.UPDATE_MOBILENO_EXCEPTION);
	}
	
	
	
}
