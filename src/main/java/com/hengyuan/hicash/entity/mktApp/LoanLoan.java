package com.hengyuan.hicash.entity.mktApp;

import java.util.Date;

import com.hengyuan.hicash.utils.LoanPeriod;

public class LoanLoan {
	private String loanId;
	private String amount;// 借款总金额
	private String username; // 借款人用户名
	private String interestRate; // 借款利率
	private String accountId; // 账户ID
	private String title;// 借款标题
	private LoanPeriod months; // 借款期数
	private String status; // 状态
	private Date endDate; // 贷款结束时间 （没有使用）
	private Character hasAnotherLoan; // 是否有其他借款 (没有使用)
	private String productId; // 贷款ID
	private String desc;// 借款描述
	private String financeDesc;// 财务状况描述
	private String imgPath; // 借款图片 （没有使用）
	private String revokeDesc;// 撤回原因描述
	private String purpose;// 借款用途
	private Boolean noticeProcessFlag;
	private String percent; // 投标金额比率
	private String completedInvestAmt; // 完成投标金额
	private String totalInvertsSum; // 总共金额

	private String applicationId; // 申请单流水号

	private String chargeFeeAmt; // 手续费金额
	private String payedChargeFeeAmt; // 已支付手续费金额

	private String createTime;

	private String custRate;
	
	private String coupon;

	public String getCustRate() {
		return custRate;
	}

	public void setCustRate(String custRate) {
		this.custRate = custRate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LoanPeriod getMonths() {
		return months;
	}

	public void setMonths(LoanPeriod months) {
		this.months = months;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Character getHasAnotherLoan() {
		return hasAnotherLoan;
	}

	public void setHasAnotherLoan(Character hasAnotherLoan) {
		this.hasAnotherLoan = hasAnotherLoan;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getFinanceDesc() {
		return financeDesc;
	}

	public void setFinanceDesc(String financeDesc) {
		this.financeDesc = financeDesc;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}

	public String getRevokeDesc() {
		return revokeDesc;
	}

	public void setRevokeDesc(String revokeDesc) {
		this.revokeDesc = revokeDesc;
	}

	public String getPurpose() {
		return purpose;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public Boolean getNoticeProcessFlag() {
		return noticeProcessFlag;
	}

	public void setNoticeProcessFlag(Boolean noticeProcessFlag) {
		this.noticeProcessFlag = noticeProcessFlag;
	}

	public String getPercent() {
		return percent;
	}

	public void setPercent(String percent) {
		this.percent = percent;
	}

	public String getCompletedInvestAmt() {
		return completedInvestAmt;
	}

	public void setCompletedInvestAmt(String completedInvestAmt) {
		this.completedInvestAmt = completedInvestAmt;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTotalInvertsSum() {
		return totalInvertsSum;
	}

	public void setTotalInvertsSum(String totalInvertsSum) {
		this.totalInvertsSum = totalInvertsSum;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	public String getChargeFeeAmt() {
		return chargeFeeAmt;
	}

	public void setChargeFeeAmt(String chargeFeeAmt) {
		this.chargeFeeAmt = chargeFeeAmt;
	}

	public String getPayedChargeFeeAmt() {
		return payedChargeFeeAmt;
	}

	public void setPayedChargeFeeAmt(String payedChargeFeeAmt) {
		this.payedChargeFeeAmt = payedChargeFeeAmt;
	}

	public String getLoanId() {
		return loanId;
	}

	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

}
