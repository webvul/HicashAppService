package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新注册信息异常
 * @author Andy.Niu
 *
 */
public class SaveRegisInfoException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7121966756131417446L;
	
	
	public SaveRegisInfoException(){
		super(ExceptionMsg.SAVE_REGISINFO_EXCEPTION);
	}
	

}
