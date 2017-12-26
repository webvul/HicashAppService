package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建联系信息异常
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class SaveContactException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3156124893932219224L;
	
	public SaveContactException(){
		super(ExceptionMsg.SAVE_CONTACT_EXCEPTION);
	}
	
	

}
