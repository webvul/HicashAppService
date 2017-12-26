package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建申请件(Pay)异常
 * @author Andy.Niu
 * @create 2014-08-06
 */
public class CreateAppPayException extends Exception{

	private static final long serialVersionUID = -2214937939595616825L;
	
	public CreateAppPayException(){
		super(ExceptionMsg.INSERT_PAY_EXCEPTION);
	}
}
