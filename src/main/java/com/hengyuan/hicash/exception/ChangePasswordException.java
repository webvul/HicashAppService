package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 修改密码异常
 * @author Andy.Niu
 *
 */
public class ChangePasswordException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6384114931394885064L;
	
	public ChangePasswordException(){
		super(ExceptionMsg.CHANGE_PASSWORD_EXCEPTION);
	}
	
	
	
}
