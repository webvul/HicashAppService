package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 命中规则
 * @author Cary.Liu
 *
 */
public class RuleValException extends Exception {

	private static final long serialVersionUID = -8057967969808322808L;

	public RuleValException(){
		super(ExceptionMsg.RULE_IN_EXCEPTION);
	}
	
}
