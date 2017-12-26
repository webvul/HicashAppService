package com.hengyuan.hicash.exception;

import com.hengyuan.hicash.constant.ExceptionMsg;


/**
 * Loan_loan表修改异常
 * 
 * @author LiHua.Ren
 * @create 2014-12-18
 */
public class LoanLoanException extends Exception {


	private static final long serialVersionUID = -7145503841172024482L;
	
	
	public LoanLoanException(){
		super(ExceptionMsg.LOAN_LOAN_EXCEPTION);
	}

}
