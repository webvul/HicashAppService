package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;

public class HyReportREcordException extends Exception {

	private static final long serialVersionUID = -5252667584428149667L;

	public HyReportREcordException(){
		super(ExceptionMsg.HINS_REPORT_RECORD_EXCEPTION);
	}

}
