package com.hengyuan.hicash.utils.exception;

import com.hengyuan.hicash.utils.UtilsMsg;

public class FileNotDirectoryException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public FileNotDirectoryException(){
		super(UtilsMsg.FILE_NOT_DIR);
	}
}
