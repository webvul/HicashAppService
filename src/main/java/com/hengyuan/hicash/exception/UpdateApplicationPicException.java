package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新申请件图片异常
 * @author Cary.Liu
 *
 */
public class UpdateApplicationPicException extends Exception {

	private static final long serialVersionUID = -6684398461358506441L;

	public UpdateApplicationPicException(){
		super(ExceptionMsg.UPDATE_APP_PIC_EXCEPTION);
	}
	
}
