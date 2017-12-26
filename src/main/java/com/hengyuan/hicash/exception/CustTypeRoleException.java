package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 客户申请权限不够
 * @author Cary.Liu
 *
 */
public class CustTypeRoleException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public CustTypeRoleException(){
		super(ExceptionMsg.CUSTTYPE_ROLE_EXCEPTION);
	}
	
}
