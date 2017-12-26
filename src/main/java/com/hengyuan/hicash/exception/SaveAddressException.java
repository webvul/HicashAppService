package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 新增地址异常
 * @author ding
 *
 */
public class SaveAddressException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5182001820827849998L;
	
	public SaveAddressException(){
		super("新增地址异常");
	}
	
	
}
