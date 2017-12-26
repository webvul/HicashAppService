package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新临时申请异常
 * @author Cary.Liu
 *
 */
public class UpdateTempAppException extends Exception {

	private static final long serialVersionUID = -8805216769766978348L;

	public UpdateTempAppException(){
		super(ExceptionMsg.UPDATE_TEMPAPP_EXCEPTION);
	}
	
}
