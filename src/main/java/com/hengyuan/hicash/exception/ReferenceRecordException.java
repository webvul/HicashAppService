package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 统计推荐人信息接口
 * 
 * @author lihua.ren
 * @createDate 2015-10-13
 *
 */
public class ReferenceRecordException extends Exception{
	private static final long serialVersionUID = 6222409204052252109L;
	
	public ReferenceRecordException(){
		super(ExceptionMsg.SAVE_REFERENCERECORD_EXCEPTION);
	}
	
}
