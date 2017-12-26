package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;





/**
 * 远程服务器链接异常
 * @author Andy.Niu
 *
 */
public class HttpReturnException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3076901448906801666L;
	public static final String HTTP_RETURN_EXCEPTION = "服务器链接失败，返回码：";
	
	public HttpReturnException(int code){
		super(ExceptionMsg.HTTP_RETURN_EXCEPTION + code);
	}

}
