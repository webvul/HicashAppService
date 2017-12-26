package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 邀请码未找到
 * @author Cary.Liu
 *
 */
public class InviteCodeNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public InviteCodeNotFoundException(){
		super(ExceptionMsg.INVITECODE_NOTFOUND_EXCEPTION);
	}
	
}
