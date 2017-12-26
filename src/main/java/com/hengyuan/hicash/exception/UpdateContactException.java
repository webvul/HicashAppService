package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


/**
 * 更新客户联系信息异常
 * @author Andy.Niu
 * @create 2014-08-03
 *
 */
public class UpdateContactException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6867872488441334618L;
	
	public UpdateContactException(){
		super(ExceptionMsg.UPDATE_CONTACT_EXCEPTION);
	}

	
}
