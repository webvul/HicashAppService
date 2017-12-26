package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/** 
 * @author dong.liu 
 * 2017-4-7 下午4:04:56
 * 类说明 :授信订单更新异常
 */
public class UpdateCreateCreditException extends Exception{
	
	
	private static final long serialVersionUID = -6684398461358506441L;

	public UpdateCreateCreditException(){
		super(ExceptionMsg.DDSJ_CREDIT_UPDATE_EXCEPTION);
	}

}
