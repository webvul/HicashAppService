package com.hengyuan.hicash.entity.param;

public class AutoBatchRuleEntity {

	/** 申请单号 */
	private String appNo;

	/** 信贷产品ID */
	private String loanProduct;

	/** 申请金额 */
	private String applyAmount;

	/** 客户用户名 */
	private String userName;

	/** 客户类型 */
	private String custType;

	/** 状态 0 ：为跑批，1：跑批结束 */
	private String status;

	/** 创建时间 */
	private String createTime;

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
