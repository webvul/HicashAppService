package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 白领取消申请
 * 
 * @author Cary.Liu
 * 
 *
 */
public class CancelCallarAppException extends Exception {

	private static final long serialVersionUID = 2180058122573681201L;

	public CancelCallarAppException(){
		super(ExceptionMsg.CANCLE_CALLRA_APPPAY_EXCEPTION);
	}
	
}
