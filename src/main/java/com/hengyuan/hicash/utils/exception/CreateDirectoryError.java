package com.hengyuan.hicash.utils.exception;

import com.hengyuan.hicash.utils.UtilsMsg;


public class CreateDirectoryError extends Exception{
	
	private static final long serialVersionUID = 1L;

	public CreateDirectoryError(){
		super(UtilsMsg.CRATE_DIR_ERROR);
	}
}
