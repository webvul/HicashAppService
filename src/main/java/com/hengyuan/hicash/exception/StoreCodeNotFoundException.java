package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 门店号未找到
 * @author Cary.Liu
 *
 */
public class StoreCodeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public StoreCodeNotFoundException(){
		super(ExceptionMsg.STORECODE_NOTFOUND_EXCEPTION);
	}
	
}
