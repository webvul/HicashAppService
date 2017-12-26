package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新单位信息异常
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class UpdateUnitException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3913496999915989112L;
	
	
	public UpdateUnitException(){
		super(ExceptionMsg.UPDATE_UNIT_EXCEPTION);
	}

}
