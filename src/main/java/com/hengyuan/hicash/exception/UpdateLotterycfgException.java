package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class UpdateLotterycfgException extends Exception {

	private static final long serialVersionUID = 1L;

	public UpdateLotterycfgException(){
		super(ExceptionMsg.UPDATE_LOTTERYCFG_EXCEPTION);
	}
	
}
