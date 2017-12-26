package com.hengyuan.hicash.entity.app;

/**
 * 还款计划实体
 * @author Cary.Liu
 *
 */
public class RepayPlanEntity {

	/** 用户名 */
	private String userName;

	/** 当前还款期数 */
	private String currentTerm;

	/** 总还款期数 */
	private String totalTerm;

	/** 还款时间 */
	private String dueDate;

	/** 本期应还金额 */
	private String principal;

	/** 本期实还金额 */
	private String prinPaid;

	/** 本期实际还款金额(app现用字段) */
	private String totalAmtPaid;

	/** 逾期金额（暂未计算） */
	private String lateMoney;

	/** 利息 */
	private String interest;

	/** 月管理费 */
	private String mthFee;

	/** 每期还款状态 */
	private String repayStatus;

	private boolean showIndex;

	/** 还款总金额 */
	private String sumprincipal;

	public String getTotalAmtPaid() {
		return totalAmtPaid;
	}

	public void setTotalAmtPaid(String totalAmtPaid) {
		this.totalAmtPaid = totalAmtPaid;
	}

	public String getLateMoney() {
		return lateMoney;
	}

	public void setLateMoney(String lateMoney) {
		this.lateMoney = lateMoney;
	}

	public String getPrinPaid() {
		return prinPaid;
	}

	public void setPrinPaid(String prinPaid) {
		this.prinPaid = prinPaid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getMthFee() {
		return mthFee;
	}

	public void setMthFee(String mthFee) {
		this.mthFee = mthFee;
	}

	public String getRepayStatus() {
		return repayStatus;
	}

	public void setRepayStatus(String repayStatus) {
		this.repayStatus = repayStatus;
	}

	public boolean isShowIndex() {
		return showIndex;
	}

	public void setShowIndex(boolean showIndex) {
		this.showIndex = showIndex;
	}

	public String getCurrentTerm() {
		return currentTerm;
	}

	public void setCurrentTerm(String currentTerm) {
		this.currentTerm = currentTerm;
	}

	public String getTotalTerm() {
		return totalTerm;
	}

	public void setTotalTerm(String totalTerm) {
		this.totalTerm = totalTerm;
	}

	public String getSumprincipal() {
		return sumprincipal;
	}

	public void setSumprincipal(String sumprincipal) {
		this.sumprincipal = sumprincipal;
	}

}
