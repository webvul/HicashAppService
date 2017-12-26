package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新密码失败
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class UpdatePassWordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2196406471814065107L;
	
	public UpdatePassWordException(){
		super(ExceptionMsg.UPDATE_PASSWORD_EXCEPTION);
	}
	
	
	
}
