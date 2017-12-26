package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


/**
 * 创建custUser异常
 * @author Andy.Niu
 * @create 2014-08-01
 */
public class SaveCustUserException extends Exception{
	

	private static final long serialVersionUID = 2332407140864068147L;

	public SaveCustUserException(){
		super(ExceptionMsg.CREATE_USER_EXCEPTION);
	}
	
}
