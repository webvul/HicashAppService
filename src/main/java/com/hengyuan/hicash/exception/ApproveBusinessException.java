package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 销售代表和学校分配关系异常
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class ApproveBusinessException extends Exception{
	
	private static final long serialVersionUID = -6510325879462592858L;
	
	
	public ApproveBusinessException(){
		super(ExceptionMsg.APPROVE_BUSI_NOT_FOUND);
	}
	
	

}
