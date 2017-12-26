package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建INPUT PAY异常
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class CreateInputPayException extends Exception{

	private static final long serialVersionUID = -2191392472957260035L;
	
	public CreateInputPayException(){
		super(ExceptionMsg.INPUT_PAY_NOT_FOUND);
	}

}
