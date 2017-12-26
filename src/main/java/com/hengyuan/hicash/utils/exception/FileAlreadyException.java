package com.hengyuan.hicash.utils.exception;

import com.hengyuan.hicash.utils.UtilsMsg;


public class FileAlreadyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public FileAlreadyException(){
		super(UtilsMsg.DIR_ALREADY);
	}
}
