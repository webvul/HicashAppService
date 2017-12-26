package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 保存单位信息异常
 * @author Andy.Niu
 *
 */
public class SaveUnitException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4972387781630263420L;
	
	public SaveUnitException(){
		super(ExceptionMsg.SAVE_UNIT_EXCEPTION);
	}
	
	
}
