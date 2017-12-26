package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 未找到用户临时申请
 * @author Cary.Liu
 * @createDate 2015-05-29
 *
 */
public class TempApplyNotFoundException extends Exception {

	private static final long serialVersionUID = -3204921225437667905L;

	public TempApplyNotFoundException(){
		super(ExceptionMsg.TEMPAPPLY_NOTFOUND_EXCEPTION);
	}
	
}
