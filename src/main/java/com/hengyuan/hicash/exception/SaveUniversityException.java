package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 保存学校信息失败
 * @author Andy.Niu
 * @create 2014-08-03
 */
public class SaveUniversityException extends Exception{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7694088357627462886L;

	public SaveUniversityException(){
		super(ExceptionMsg.SAVE_UNIVERSITY_EXCEPTION);
	}
}
