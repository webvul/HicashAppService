package com.hengyuan.hicash.exception;

/**
 * 验证码超时
 * @author Cary.Liu
 *
 */
public class IdentifyOverTimeException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdentifyOverTimeException(String msg){
		super(msg);
	}
	
}
