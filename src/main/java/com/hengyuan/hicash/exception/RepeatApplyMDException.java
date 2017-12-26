package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 嗨秒贷商品重复申请
 * @author Cary.Liu
 * @createDate 2015-07-19
 *
 */
public class RepeatApplyMDException extends Exception {

	private static final long serialVersionUID = 1L;

	public RepeatApplyMDException(){
		super(ExceptionMsg.REPEATAPPLY_MD_EXCEPTION);
	}
	
}
