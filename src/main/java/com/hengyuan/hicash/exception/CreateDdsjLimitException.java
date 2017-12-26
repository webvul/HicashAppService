package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建滴滴司机额度信息异常
 * @author Andy.Niu
 * @create 2014-08-06
 */
public class CreateDdsjLimitException extends Exception{

	private static final long serialVersionUID = -2214937939595616825L;
	
	public CreateDdsjLimitException(){
		super(ExceptionMsg.DDSJ_LIMIT_EXCEPTION);
	}
}
