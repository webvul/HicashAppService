package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 门店号无效（审核未通过）
 * @author think
 *
 */
public class StoreCodeInvalidException extends Exception {

	private static final long serialVersionUID = 1L;

	public StoreCodeInvalidException(){
		super(ExceptionMsg.STORECODE_INVALID_EXCEPTION);
	}
	
}
