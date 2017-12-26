package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新信息失败
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class UpdateInfoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4956922155240460178L;

	public UpdateInfoException(){
		super(ExceptionMsg.UPDATE_INFO_EXCEPTION);
	}
	
}
