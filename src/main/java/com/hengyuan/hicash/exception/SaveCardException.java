package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 保存银行卡信息异常
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class SaveCardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4374679945035296459L;
	
	
	public SaveCardException(){
		super(ExceptionMsg.SAVE_CARD_EXCEPTION);
	}
	
	
	

}
