package com.hengyuan.hicash.entity;

/**
 * 
 * @author teng
 *
 */
public class RepayProgramEntity {

	/** 贷款产品ID */
	private String loanProduct;
	/** 期数 */
	private String installments;

	private String name; //信贷产品名称
	
	private String mthFee;//月管理费率
	
	private String custRate;//客户费率
	
	private String serverRate;//服务费率
	
	private String monthPayAmt;//月还款
	
	private String timeLimit;//期限
	
	private String periods;//app显示单位

	public String getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(String timeLimit) {
		this.timeLimit = timeLimit;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMthFee() {
		return mthFee;
	}

	public void setMthFee(String mthFee) {
		this.mthFee = mthFee;
	}

	public String getCustRate() {
		return custRate;
	}

	public void setCustRate(String custRate) {
		this.custRate = custRate;
	}

	public String getServerRate() {
		return serverRate;
	}

	public void setServerRate(String serverRate) {
		this.serverRate = serverRate;
	}

	public String getMonthPayAmt() {
		return monthPayAmt;
	}

	public void setMonthPayAmt(String monthPayAmt) {
		this.monthPayAmt = monthPayAmt;
	}

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}
	

}
