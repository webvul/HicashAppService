package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class BankCardNotVerifiedException extends Exception{
	
	/**
	 * 未查到该银行卡的验证记录
	 */
	private static final long serialVersionUID = -8306171388783668919L;

	public BankCardNotVerifiedException(){
		super(ExceptionMsg.BANK_CARD_NOT_VERIFIED_EXCEPTION);
	}	
	
}
