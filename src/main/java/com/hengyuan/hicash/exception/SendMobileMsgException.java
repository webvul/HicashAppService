package com.hengyuan.hicash.exception;

public class SendMobileMsgException extends Exception {
	
	private static final long serialVersionUID = -6493493416062866984L;

	public SendMobileMsgException(){
		super("发送短信认证失败！");
	}
	
}
