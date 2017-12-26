package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新客户信息异常
 * @author Cary.Liu
 *
 */
public class UpdateCustomerException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateCustomerException(){
		super(ExceptionMsg.UPDATE_CUSTOMER_EXCEPTION);
	}
	
}
