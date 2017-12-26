package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * @author 商品价格错误
 *
 */
public class ProPriceFalseException extends Exception {

	private static final long serialVersionUID = 1L;

	public ProPriceFalseException(){
		super(ExceptionMsg.PRO_PRICE_FALSE);
	}

}
