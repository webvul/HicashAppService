package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 保存自动规则申请件异常
 * @author think
 *
 */
public class SaveAutoRuleAppException extends Exception {

	private static final long serialVersionUID = 1L;

	public SaveAutoRuleAppException(){
		super(ExceptionMsg.SAVE_AUTORULEAPP_EXCEPTION);
	}
	
}
