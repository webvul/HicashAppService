package com.hengyuan.hicash.utils.exception;

/**
 * 工具包异常
 * 传入的list为空
 * @author andy.niu
 *
 */
public class ListNotFoundException extends Exception{
	
	private static final long serialVersionUID = 1L;
	
	public ListNotFoundException(String message){
		super(message);
	}
}
