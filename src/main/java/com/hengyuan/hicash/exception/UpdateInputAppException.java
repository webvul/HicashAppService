package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 修改申请件信息表异常
 * @author Cary.Liu
 * @create 2014-08-18
 *
 */
public class UpdateInputAppException extends Exception {

	private static final long serialVersionUID = 5332431112160477741L;

	public UpdateInputAppException(){
		super(ExceptionMsg.UPDATE_INPUT_APP_EXCEPTION);
	}
	
}
