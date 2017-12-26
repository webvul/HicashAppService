package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 滴滴贷限制
 * @author ding
 *
 */
public class DDNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DDNotFoundException(){
		super(ExceptionMsg.DD_NOT_FOUND);
	}
	
}
