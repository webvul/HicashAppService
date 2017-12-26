package com.hengyuan.hicash.parameters.response.amount;

import java.util.List;

import com.hengyuan.hicash.entity.app.AccountPay;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 查询申请件进度 返回参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-24
 *
 */
public class AppScheduleResp extends ParmResponse {

	/** 可用额度 */
	private String availCredit;

	/** 授信总额度 */
	private String creditAmt;

	/** 可用余额 */
	private String balance;

	/** 近7天待还款总额 */
	private String lateTotal;

	/** 当前逾期笔数 */
	private String overDueCount;

	/** 申请件集合 */
	private List<AccountPay> appList;

	/** 还款中申请件 */
	private List<AccountPay> repayPlans;

	public List<AccountPay> getRepayPlans() {
		return repayPlans;
	}

	public void setRepayPlans(List<AccountPay> repayPlans) {
		this.repayPlans = repayPlans;
	}

	public String getAvailCredit() {
		return availCredit;
	}

	public void setAvailCredit(String availCredit) {
		this.availCredit = availCredit;
	}

	public String getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(String creditAmt) {
		this.creditAmt = creditAmt;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getLateTotal() {
		return lateTotal;
	}

	public void setLateTotal(String lateTotal) {
		this.lateTotal = lateTotal;
	}

	public String getOverDueCount() {
		return overDueCount;
	}

	public void setOverDueCount(String overDueCount) {
		this.overDueCount = overDueCount;
	}

	public List<AccountPay> getAppList() {
		return appList;
	}

	public void setAppList(List<AccountPay> appList) {
		this.appList = appList;
	}

}
