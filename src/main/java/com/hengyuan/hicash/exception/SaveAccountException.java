package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建账户信息异常
 * @author Andy.Niu
 * @create 2014-08-01
 */
public class SaveAccountException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5182001820827849998L;
	
	public SaveAccountException(){
		super(ExceptionMsg.CREATE_ACCOUNT_EXCEPTION);
	}
	
	
}
