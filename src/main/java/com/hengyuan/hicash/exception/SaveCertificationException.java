package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 创建新的认证项失败
 * @author Andy.Niu
 * @create 2014-08-04
 */
public class SaveCertificationException extends Exception{

	private static final long serialVersionUID = 6222409204052252109L;
	
	public SaveCertificationException(){
		super(ExceptionMsg.SAVE_CERTIFICATION_EXCEPTION);
	}
	
	
	

}
