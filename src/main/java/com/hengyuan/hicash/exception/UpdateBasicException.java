package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新基本信息异常
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class UpdateBasicException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1125027598565063105L;
	
	public UpdateBasicException(){
		super(ExceptionMsg.UPDATE_BASIC_INFO_EXCEPTION);
	}
	
	

}
