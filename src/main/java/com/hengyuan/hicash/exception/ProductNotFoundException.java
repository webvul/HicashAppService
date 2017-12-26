package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 实物产品未找到
 * @author Andy.Niu
 * @create 2014-08-06
 */
public class ProductNotFoundException extends Exception{

	private static final long serialVersionUID = 1836572527792030002L;

	public ProductNotFoundException(){
		super(ExceptionMsg.PRODUCT_NOT_FOUND);
	}
	
	
}
