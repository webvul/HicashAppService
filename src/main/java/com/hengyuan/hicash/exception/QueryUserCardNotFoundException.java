package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建INPUT PAY 卡号信息异常
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class QueryUserCardNotFoundException extends Exception{

	private static final long serialVersionUID = -2191392472957260035L;
	
	public QueryUserCardNotFoundException(){
		super(ExceptionMsg.QUERY_USERCARD_NOTFOUND_EXCEPTION);
	}

}
