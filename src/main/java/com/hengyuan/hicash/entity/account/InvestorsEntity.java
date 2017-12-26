package com.hengyuan.hicash.entity.account;

public class InvestorsEntity {
	
	private String id;
	
	/**投资人*/
	private String inversterName;
	
	/**投资人账户id*/
	private String accountId;
	
	/**规则开始时间*/
	private String applyStart;
	
	/**规则结束时间*/
	private String applyEnd;

	/**总笔数限制*/
	private String totalCountLimit;
	
	/**总金额限制*/
	private String totalAmtLimit;
	
	/**月笔数限制*/
	private String monthCountLimit;
	
	/**月金额限制*/
	private String monthAmtLimit;
	
	/**开始时间*/
	private String startDate;
	
	/**结束时间*/
	private String endDate;
	
	
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getInversterName() {
		return inversterName;
	}

	public void setInversterName(String inversterName) {
		this.inversterName = inversterName;
	}

	public String getApplyStart() {
		return applyStart;
	}

	public void setApplyStart(String applyStart) {
		this.applyStart = applyStart;
	}

	public String getApplyEnd() {
		return applyEnd;
	}

	public void setApplyEnd(String applyEnd) {
		this.applyEnd = applyEnd;
	}

	public String getTotalCountLimit() {
		return totalCountLimit;
	}

	public void setTotalCountLimit(String totalCountLimit) {
		this.totalCountLimit = totalCountLimit;
	}

	public String getTotalAmtLimit() {
		return totalAmtLimit;
	}

	public void setTotalAmtLimit(String totalAmtLimit) {
		this.totalAmtLimit = totalAmtLimit;
	}

	public String getMonthCountLimit() {
		return monthCountLimit;
	}

	public void setMonthCountLimit(String monthCountLimit) {
		this.monthCountLimit = monthCountLimit;
	}

	public String getMonthAmtLimit() {
		return monthAmtLimit;
	}

	public void setMonthAmtLimit(String monthAmtLimit) {
		this.monthAmtLimit = monthAmtLimit;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	
	
	
	
}
