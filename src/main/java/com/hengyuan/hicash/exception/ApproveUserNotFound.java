package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 后台用户对象未找到
 * @author Cary.Liu
 * @create 2014-08-07
 */
public class ApproveUserNotFound extends Exception{

	private static final long serialVersionUID = 6544281248005231336L;
	
	public ApproveUserNotFound(){
		super(ExceptionMsg.APPROVE_USER_NOT_FOUND);
	}

	
	
}
