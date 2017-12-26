package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 更新用户页面索引异常
 * @author Cary.Liu
 * @create 2014-08-15
 *
 */
public class UpdatePageIndexException extends Exception {

	private static final long serialVersionUID = 5159167949152235929L;

	public UpdatePageIndexException(){
		super(ExceptionMsg.UPDATE_PAGE_INDEX_EXCEPTION);
	}
	
}
