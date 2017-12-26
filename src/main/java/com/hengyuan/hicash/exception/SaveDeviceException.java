package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class SaveDeviceException extends Exception {

	private static final long serialVersionUID = 1L;

	public SaveDeviceException(String msg){
		super(ExceptionMsg.SAVE_INFO_EXCEPTION);
	}
	
}
