package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新绑定银行卡信息异常
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class UpdateCardException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -431875594756751112L;
	
	public UpdateCardException(){
		super(ExceptionMsg.UPDATE_CARD_EXCEPTION);
	}

}
