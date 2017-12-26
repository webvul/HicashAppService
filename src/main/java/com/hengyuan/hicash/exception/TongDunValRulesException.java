package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

/**
 * 同盾接口验证客户黑名单，客户身份证号码、手机号码、邮箱、qq
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
public class TongDunValRulesException extends Exception{

	private static final long serialVersionUID = -3453520519564844687L;
	
	public TongDunValRulesException(){
		super(ExceptionMsg.TONGDUN_VALRULES_EXCEPTION);
	}

}
