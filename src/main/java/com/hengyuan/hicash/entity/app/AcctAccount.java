package com.hengyuan.hicash.entity.app;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;



public class AcctAccount {
    private String Id;
	//用户名
	private String username;
	//可用金额
	private BigDecimal balance;
	//冻结金额
	private BigDecimal freezeAmount;
	//贷出应收本金
	private BigDecimal debitPrinciple;
    //贷出应收利息
	private BigDecimal debitInterest;
	//应收逾期本金
	private BigDecimal overDuePrincipleD;
	//应收逾期利息
	private BigDecimal overDurInterestD;
	//应收罚息
	private BigDecimal penaltyD;
	//应收滞纳金
	private BigDecimal lateFeeD;
	//应收违约金
	private BigDecimal breachContractD;
	//应收逾期总金额
	private BigDecimal overDueAmounD;
    //借入应付本金
	private BigDecimal creditPrinciple;
	//借入应付利息
	private BigDecimal creditInterest;
	//应付逾期本金
	private BigDecimal overDuePrincipleC;
	//应付逾期利息
	private BigDecimal overDueInterestC;
	//应付罚息
	private BigDecimal penaltyC;
	//应付滞纳金
	private BigDecimal lateFeeC;
	//应付违约金
	private BigDecimal breachContractC;
	//应付逾期总金额
	private BigDecimal overDueAmountC;
	//总信用额度
	private BigDecimal creditLimit;
	private BigDecimal creditLimitBlance;
	//跑批时间
	private Date batchTime;
	//借入笔数
	private Integer totalLoanTimes;
	//借入成功笔数
	private Integer loanSuccTimes;
	//借入金额
	private BigDecimal totalLoanAmount;
	//预授权金额
	private BigDecimal preAuthorizationAmount;
	//账户类型
	private String accountType;
	
   //支付方式
	private List<String> payMentWay;
	
	
	
	

	public AcctAccount() {
	}

	public AcctAccount(String username, BigDecimal balance,
			BigDecimal freezeAmount, BigDecimal debitPrinciple,
			BigDecimal debitInterest, BigDecimal overDuePrincipleD,
			BigDecimal overDurInterestD, BigDecimal penaltyD,
			BigDecimal lateFeeD, BigDecimal breachContractD,
			BigDecimal overDueAmounD, BigDecimal creditPrinciple,
			BigDecimal creditInterest, BigDecimal overDuePrincipleC,
			BigDecimal overDueInterestC, BigDecimal penaltyC,
			BigDecimal lateFeeC, BigDecimal breachContractC,
			BigDecimal overDueAmountC, BigDecimal creditLimit, Date batchTime) {
		this.username = username;
		this.balance = balance;
		this.freezeAmount = freezeAmount;
		this.debitPrinciple = debitPrinciple;
		this.debitInterest = debitInterest;
		this.overDuePrincipleD = overDuePrincipleD;
		this.overDurInterestD = overDurInterestD;
		this.penaltyD = penaltyD;
		this.lateFeeD = lateFeeD;
		this.breachContractD = breachContractD;
		this.overDueAmounD = overDueAmounD;
		this.creditPrinciple = creditPrinciple;
		this.creditInterest = creditInterest;
		this.overDuePrincipleC = overDuePrincipleC;
		this.overDueInterestC = overDueInterestC;
		this.penaltyC = penaltyC;
		this.lateFeeC = lateFeeC;
		this.breachContractC = breachContractC;
		this.overDueAmountC = overDueAmountC;
		this.creditLimit = creditLimit;
		this.batchTime = batchTime;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}

	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}

	public BigDecimal getDebitPrinciple() {
		return debitPrinciple;
	}

	public void setDebitPrinciple(BigDecimal debitPrinciple) {
		this.debitPrinciple = debitPrinciple;
	}

	public BigDecimal getDebitInterest() {
		return debitInterest;
	}

	public void setDebitInterest(BigDecimal debitInterest) {
		this.debitInterest = debitInterest;
	}

	public BigDecimal getOverDuePrincipleD() {
		return overDuePrincipleD;
	}

	public void setOverDuePrincipleD(BigDecimal overDuePrincipleD) {
		this.overDuePrincipleD = overDuePrincipleD;
	}

	public BigDecimal getOverDurInterestD() {
		return overDurInterestD;
	}

	public void setOverDurInterestD(BigDecimal overDurInterestD) {
		this.overDurInterestD = overDurInterestD;
	}

	public BigDecimal getPenaltyD() {
		return penaltyD;
	}

	public void setPenaltyD(BigDecimal penaltyD) {
		this.penaltyD = penaltyD;
	}

	public BigDecimal getLateFeeD() {
		return lateFeeD;
	}

	public void setLateFeeD(BigDecimal lateFeeD) {
		this.lateFeeD = lateFeeD;
	}

	public BigDecimal getBreachContractD() {
		return breachContractD;
	}

	public void setBreachContractD(BigDecimal breachContractD) {
		this.breachContractD = breachContractD;
	}

	public BigDecimal getOverDueAmounD() {
		return overDueAmounD;
	}

	public void setOverDueAmounD(BigDecimal overDueAmounD) {
		this.overDueAmounD = overDueAmounD;
	}

	public BigDecimal getCreditPrinciple() {
		return creditPrinciple;
	}

	public void setCreditPrinciple(BigDecimal creditPrinciple) {
		this.creditPrinciple = creditPrinciple;
	}

	public BigDecimal getCreditInterest() {
		return creditInterest;
	}

	public void setCreditInterest(BigDecimal creditInterest) {
		this.creditInterest = creditInterest;
	}

	public BigDecimal getOverDuePrincipleC() {
		return overDuePrincipleC;
	}

	public void setOverDuePrincipleC(BigDecimal overDuePrincipleC) {
		this.overDuePrincipleC = overDuePrincipleC;
	}

	public BigDecimal getOverDueInterestC() {
		return overDueInterestC;
	}

	public void setOverDueInterestC(BigDecimal overDueInterestC) {
		this.overDueInterestC = overDueInterestC;
	}

	public BigDecimal getPenaltyC() {
		return penaltyC;
	}

	public void setPenaltyC(BigDecimal penaltyC) {
		this.penaltyC = penaltyC;
	}

	public BigDecimal getLateFeeC() {
		return lateFeeC;
	}

	public void setLateFeeC(BigDecimal lateFeeC) {
		this.lateFeeC = lateFeeC;
	}

	public BigDecimal getBreachContractC() {
		return breachContractC;
	}

	public void setBreachContractC(BigDecimal breachContractC) {
		this.breachContractC = breachContractC;
	}

	public BigDecimal getOverDueAmountC() {
		return overDueAmountC;
	}

	public void setOverDueAmountC(BigDecimal overDueAmountC) {
		this.overDueAmountC = overDueAmountC;
	}

	public BigDecimal getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}

	public BigDecimal getCreditLimitBlance() {
		return creditLimitBlance;
	}

	public void setCreditLimitBlance(BigDecimal creditLimitBlance) {
		this.creditLimitBlance = creditLimitBlance;
	}

	public Date getBatchTime() {
		return batchTime;
	}

	public void setBatchTime(Date batchTime) {
		this.batchTime = batchTime;
	}

	public Integer getTotalLoanTimes() {
		return totalLoanTimes;
	}

	public void setTotalLoanTimes(Integer totalLoanTimes) {
		this.totalLoanTimes = totalLoanTimes;
	}

	public Integer getLoanSuccTimes() {
		return loanSuccTimes;
	}

	public void setLoanSuccTimes(Integer loanSuccTimes) {
		this.loanSuccTimes = loanSuccTimes;
	}

	public BigDecimal getTotalLoanAmount() {
		return totalLoanAmount;
	}

	public void setTotalLoanAmount(BigDecimal totalLoanAmount) {
		this.totalLoanAmount = totalLoanAmount;
	}

	public BigDecimal getPreAuthorizationAmount() {
		return preAuthorizationAmount;
	}

	public void setPreAuthorizationAmount(BigDecimal preAuthorizationAmount) {
		this.preAuthorizationAmount = preAuthorizationAmount;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public List<String> getPayMentWay() {
		return payMentWay;
	}

	public void setPayMentWay(List<String> payMentWay) {
		this.payMentWay = payMentWay;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}



	
}
