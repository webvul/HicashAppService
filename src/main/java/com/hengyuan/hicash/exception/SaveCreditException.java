package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 保存资信信息异常
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class SaveCreditException extends Exception{


	private static final long serialVersionUID = -1587717369490914767L;
	
	
	public SaveCreditException(){
		super(ExceptionMsg.SAVE_CREDIT_EXCEPTION);
	}

}
