package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 同盾接口保存进黑名单
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
public class SaveTongDunBlackException extends Exception{

	private static final long serialVersionUID = -3453520519564844687L;
	
	public SaveTongDunBlackException(){
		super(ExceptionMsg.TONGDUN_SAVE_BLACK_EXCEPTION);
	}

}
