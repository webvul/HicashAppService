package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author teng
 *
 */
public class LoanAmtCalNewReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String industryCode;// 行业

	private String userName;// 用户

	private String applyCount;// 申请次数

	/*
	 * KHL0  无学信信息
	 * KHL1 在籍（全日制）
	 * KHL4 在籍（非全日制）|离毕业时间<60天|不在籍
	 */
	private String custType;
	
	private String loanAmount;//借款金额

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getApplyCount() {
		return applyCount;
	}

	public void setApplyCount(String applyCount) {
		this.applyCount = applyCount;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

}
