package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 未找到商品
 * @author Cary.Liu
 *
 */
public class MerProductNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public MerProductNotFoundException(){
		super(ExceptionMsg.MERPRODUCT_NOTFOUND_EXCEPTION);
	}
	
}
