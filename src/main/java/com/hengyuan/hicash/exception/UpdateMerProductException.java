package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新商户商品表异常
 * @author Cary.Liu
 *
 */
public class UpdateMerProductException extends Exception {

	private static final long serialVersionUID = -3021426862904300755L;

	public UpdateMerProductException(){
		super(ExceptionMsg.UPDATE_SUPPLIER_EXCEPTION);
	}
	
}
