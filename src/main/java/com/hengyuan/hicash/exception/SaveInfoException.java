package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 保存基本资料异常
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class SaveInfoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 201688873561043429L;

	public SaveInfoException(){
		super(ExceptionMsg.SAVE_INFO_EXCEPTION);
	}
	
}
