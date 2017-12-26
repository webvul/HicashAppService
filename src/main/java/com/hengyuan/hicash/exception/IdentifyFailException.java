package com.hengyuan.hicash.exception;

/**
 * 验证码错误
 * @author Cary.Liu
 *
 */
public class IdentifyFailException extends Exception {

	private static final long serialVersionUID = 1L;

	public IdentifyFailException(String msg){
		super(msg);
	}
	
}
