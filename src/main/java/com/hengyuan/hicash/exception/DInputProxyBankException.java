package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class DInputProxyBankException extends Exception{
	
	/**
	 * 更新订单表代扣银行卡失败
	 */
	private static final long serialVersionUID = -8306171388783668915L;

	public DInputProxyBankException(){
		super(ExceptionMsg.UPDATE_DINPUTAPP_BANKCARD_EXCEPTION);
	}	
	
}
