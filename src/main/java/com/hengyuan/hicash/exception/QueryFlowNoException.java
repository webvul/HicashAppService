package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 
 * 当天流水序号查询异常
 * @author Andy.Niu
 * @create date 2014-07-16
 * 
 */
public class QueryFlowNoException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public QueryFlowNoException(){
		super(ExceptionMsg.QUERY_FLOWNO_ERROR);
	}
	
	
}
