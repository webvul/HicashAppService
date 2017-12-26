package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 学校信息未查询到
 * @author Andy.Niu
 * @create 2014-08-07
 */
public class UniversityNotFound extends Exception{

	private static final long serialVersionUID = -2883778761469967300L;

	public UniversityNotFound(){
		super(ExceptionMsg.UNIVERSITY_NOT_FOUND);
	}
	
	
}
