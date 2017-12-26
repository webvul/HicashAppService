package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 秒贷关闭
 * @author ding
 *
 */
public class MDNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public MDNotFoundException(){
		super(ExceptionMsg.HD_NOT_FOUND);
	}
	
}
