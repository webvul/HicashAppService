package com.hengyuan.hicash.entity.app;

import java.math.BigDecimal;

public class AppExceptionEntity {

	private String userName;

	private String appNo;

	private String loanProductApp;

	private String loanProductLoan;

	private BigDecimal loanAmountApp;

	private BigDecimal loanAmountLoan;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getLoanProductApp() {
		return loanProductApp;
	}

	public void setLoanProductApp(String loanProductApp) {
		this.loanProductApp = loanProductApp;
	}

	public String getLoanProductLoan() {
		return loanProductLoan;
	}

	public void setLoanProductLoan(String loanProductLoan) {
		this.loanProductLoan = loanProductLoan;
	}

	public BigDecimal getLoanAmountApp() {
		return loanAmountApp;
	}

	public void setLoanAmountApp(BigDecimal loanAmountApp) {
		this.loanAmountApp = loanAmountApp;
	}

	public BigDecimal getLoanAmountLoan() {
		return loanAmountLoan;
	}

	public void setLoanAmountLoan(BigDecimal loanAmountLoan) {
		this.loanAmountLoan = loanAmountLoan;
	}

}
