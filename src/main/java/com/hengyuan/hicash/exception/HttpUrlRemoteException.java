package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 调用远程HTTP服务地址获取异常
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class HttpUrlRemoteException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2868068341293823404L;
	
	
	public HttpUrlRemoteException(){
		super(ExceptionMsg.HTTP_URL_REMOTE_EXCEPTION);
	}

	
}
