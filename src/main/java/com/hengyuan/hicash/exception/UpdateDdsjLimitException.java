package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/** 
 * @author dong.liu 
 * 2017-4-7 下午4:04:56
 * 类说明 :授信额度更新异常
 */
public class UpdateDdsjLimitException extends Exception{
	
	
	private static final long serialVersionUID = -6684398461358506441L;

	public UpdateDdsjLimitException(){
		super(ExceptionMsg.DDSJ_LIMIT_UPDATE_EXCEPTION);
	}

}
