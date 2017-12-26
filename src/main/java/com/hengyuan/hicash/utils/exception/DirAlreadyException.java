package com.hengyuan.hicash.utils.exception;

import com.hengyuan.hicash.utils.UtilsMsg;

public class DirAlreadyException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public DirAlreadyException(){
		super(UtilsMsg.DIR_ALREADY);
	}
}
