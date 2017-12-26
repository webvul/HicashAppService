package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新客户学校信息失败
 * @author Andy.Niu
 *
 */
public class UpdateCustShcoolException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 842807232924987388L;

	public UpdateCustShcoolException(){
		super(ExceptionMsg.UPDATE_CUST_SCHOOL_EXCEPTION);
	}
	
	
}
