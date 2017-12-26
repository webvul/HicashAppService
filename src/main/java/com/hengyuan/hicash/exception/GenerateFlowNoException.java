package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


/**
 * 
 * 申请件流水号生成异常
 * @author Andy.Niu
 * @create date 2014-07-16
 * 
 */
public class GenerateFlowNoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public GenerateFlowNoException(){
		super(ExceptionMsg.GENERATE_FLOWNO_ERROR);
	}
}
