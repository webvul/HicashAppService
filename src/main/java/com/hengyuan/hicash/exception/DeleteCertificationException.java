package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 删除认证项失败
 * @author Andy.Niu
 *
 */
public class DeleteCertificationException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8306171388783668915L;

	public DeleteCertificationException(){
		super(ExceptionMsg.DELETE_CERTIFICATION_EXCEPTION);
	}	
	
}
