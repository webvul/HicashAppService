package com.hengyuan.hicash.entity.app;

/**
 * 贷款对象实体
 * 
 * @author Andy.Niu
 * @create 2014-07-26
 */
public class LoanLoanAccEntity {

	// 逾期笔数计算
	private String overCount;

	// 当前期数
	private String curTerm;
	// 当前应还金额
	private String curPayAmt;
	// 逾期金额
	private String balance;
	// 剩余本金
	private String remainPrin;
	// 剩余利息
	private String remainInt;
	// 剩余费用
	private String remainFee;

	// 下一还款日
	private String nextStmtDate;

	// 每月还款日截取
	private String subDate;

	// 逾期本金
	private String unpaidPrin;
	// 逾期利息
	private String unpaidInt;
	// 逾期管理费
	private String unpaidMthFee;
	// 逾期其他费用
	private String unpaidFee;

	private String availAbleDate;

	/** 新每月还款日 */
	private String creditDay;

	// 还款总期数

	private String totalTerm;
	
	/**车抵贷 还款金额*/
	private String creditName;
	
	private String loanStatus;//贷款状态
	
	

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getCreditDay() {
		return creditDay;
	}

	public void setCreditDay(String creditDay) {
		this.creditDay = creditDay;
	}

	public String getAvailAbleDate() {
		return availAbleDate;
	}

	public void setAvailAbleDate(String availAbleDate) {
		this.availAbleDate = availAbleDate;
	}

	public String getOverCount() {
		return overCount;
	}

	public void setOverCount(String overCount) {
		this.overCount = overCount;
	}

	public String getCurTerm() {
		return curTerm;
	}

	public void setCurTerm(String curTerm) {
		this.curTerm = curTerm;
	}

	public String getCurPayAmt() {
		return curPayAmt;
	}

	public void setCurPayAmt(String curPayAmt) {
		this.curPayAmt = curPayAmt;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getRemainPrin() {
		return remainPrin;
	}

	public void setRemainPrin(String remainPrin) {
		this.remainPrin = remainPrin;
	}

	public String getRemainInt() {
		return remainInt;
	}

	public void setRemainInt(String remainInt) {
		this.remainInt = remainInt;
	}

	public String getRemainFee() {
		return remainFee;
	}

	public void setRemainFee(String remainFee) {
		this.remainFee = remainFee;
	}

	public String getNextStmtDate() {
		return nextStmtDate;
	}

	public void setNextStmtDate(String nextStmtDate) {
		this.nextStmtDate = nextStmtDate;
	}

	public String getSubDate() {
		return subDate;
	}

	public void setSubDate(String subDate) {
		this.subDate = subDate;
	}

	public String getUnpaidPrin() {
		return unpaidPrin;
	}

	public void setUnpaidPrin(String unpaidPrin) {
		this.unpaidPrin = unpaidPrin;
	}

	public String getUnpaidInt() {
		return unpaidInt;
	}

	public void setUnpaidInt(String unpaidInt) {
		this.unpaidInt = unpaidInt;
	}

	public String getUnpaidMthFee() {
		return unpaidMthFee;
	}

	public void setUnpaidMthFee(String unpaidMthFee) {
		this.unpaidMthFee = unpaidMthFee;
	}

	public String getUnpaidFee() {
		return unpaidFee;
	}

	public void setUnpaidFee(String unpaidFee) {
		this.unpaidFee = unpaidFee;
	}

	public String getTotalTerm() {
		return totalTerm;
	}

	public void setTotalTerm(String totalTerm) {
		this.totalTerm = totalTerm;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}
	
	

}
