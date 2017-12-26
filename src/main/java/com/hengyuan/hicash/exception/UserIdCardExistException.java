package com.hengyuan.hicash.exception;

/**
 * 身份证号码存在
 * @author Cary.Liu
 *
 */
public class UserIdCardExistException extends Exception {

	private static final long serialVersionUID = 1L;

	public UserIdCardExistException(String msg){
		super(msg);
	}
	
}
