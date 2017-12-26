package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新申请件异常
 * @author Andy.Niu
 * @create 2014-07-29
 */
public class UpdateAppPayException extends Exception{

	private static final long serialVersionUID = -7145503841172024482L;
	
	
	public UpdateAppPayException(){
		super(ExceptionMsg.PAY_UPDATE_EXCEPTION);
	}

}
