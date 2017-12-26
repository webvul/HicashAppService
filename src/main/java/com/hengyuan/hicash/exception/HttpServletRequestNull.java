package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * Request空异常
 * @author Cary.Liu
 *
 */
public class HttpServletRequestNull extends Exception {

	private static final long serialVersionUID = -2509140132383598681L;

	public HttpServletRequestNull(){
		super(ExceptionMsg.HTTPSERVLETREQUEST_NULL_EXCEPTION);
	}
	
}
