package com.hengyuan.hicash.entity.app;

import java.util.List;

/**
 * 用户申请件信息
 * 
 * @author Cary.Liu
 *
 */
public class AccountPay {

	/** 临时流水号 */
	private String temAppPayNo;
	
	/** 流水号 */
	private String appPayNo;

	/** 产品ID */
	private String merProductId;

	/** 信贷产品ID */
	private String loanProduct;

	/** 申请产品名称 */
	private String productName;

	/** app产品名称 */
	private String appProductName;

	/** 行业代码 */
	private String industryCode;

	/** 申请类别 NORMAL OR CASH */
	private String applyType;

	/** 申请类型名称 */
	private String applyTypeName;

	/** 还款总期数 */
	private String installMent;

	/** 申请时间（yyyy-MM-dd）格式 */
	private String applyDate;

	/** 借款金额 */
	private String loanAmount;

	/** 大节点 */
	private String allNode;

	/** 节点 */
	private String node;

	/** 状态节点 */
	private String status;

	/** 状态名称 */
	private String statusName;

	/** 申请件所在流程节点顺序 NORMAL类的分 10 20 30 40 CASH类的分 50 60 70 80 */
	private String processIndex;

	/** 客户类型 */
	private String custType;

	/** 支付首付比率 */
	private String firstPayRate;

	/** 创建时间 */
	private String createDate;

	/** 首付金额 */
	private String firstAmount;

	/** 申请金额 */
	private String applyAmount;

	/** 还款总期数 */
	private String totalTerm;

	/** 还款计划 */
	private List<RepayPlanEntity> repayPlans;

	/* 还款中参数 */

	/** 当前期数 */
	private String curTerm;

	/** 当前应还金额 */
	private String curPayAmt;

	/** 逾期金额 */
	private String balance;

	/** 剩余本金 */
	private String remainPrin;

	/** 剩余利息 */
	private String remainInt;

	/** 剩余费用 */
	private String remainFee;

	/** 下一还款日 */
	private String nextStmtDate;

	/** 每月还款金额 */
	private String mthPayFee;

	/** 每月还款日截取 */
	private String subDate;

	/** 逾期本金 */
	private String unpaidPrin;

	/** 逾期利息 */
	private String unpaidInt;

	/** 逾期管理费 */
	private String unpaidMthFee;

	/** 逾期其他费用 */
	private String unpaidFee;

	/** 还款总金额 */
	private String sumprincipal;

	/**  */
	private String treatyUploadFlag;

	/**  */
	private String treatyUploadURL;

	private String showAllNode;

	private String isCancel;

	private String isFirst;

	/** 审批驳回原因 */
	private String rejectDesc;

	/** 销售凭证驳回原因 */
	private String appcheckdesc;

	/** 上传协议（驳回）原因 */
	private String rejectCause;

	/** 商品ID */
	private String appProductId;
	
	private String creditName;
	
	
	public String getMerProductId() {
		return merProductId;
	}

	public void setMerProductId(String merProductId) {
		this.merProductId = merProductId;
	}

	public String getAppProductName() {
		return appProductName;
	}

	public void setAppProductName(String appProductName) {
		this.appProductName = appProductName;
	}

	public String getRejectDesc() {
		return rejectDesc;
	}

	public void setRejectDesc(String rejectDesc) {
		this.rejectDesc = rejectDesc;
	}

	public String getAppcheckdesc() {
		return appcheckdesc;
	}

	public void setAppcheckdesc(String appcheckdesc) {
		this.appcheckdesc = appcheckdesc;
	}

	public String getRejectCause() {
		return rejectCause;
	}

	public void setRejectCause(String rejectCause) {
		this.rejectCause = rejectCause;
	}

	public String getApplyTypeName() {
		return applyTypeName;
	}

	public void setApplyTypeName(String applyTypeName) {
		this.applyTypeName = applyTypeName;
	}

	public String getShowAllNode() {
		return showAllNode;
	}

	public void setShowAllNode(String showAllNode) {
		this.showAllNode = showAllNode;
	}

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getIsFirst() {
		return isFirst;
	}

	public void setIsFirst(String isFirst) {
		this.isFirst = isFirst;
	}

	public String getAppPayNo() {
		return appPayNo;
	}

	public void setAppPayNo(String appPayNo) {
		this.appPayNo = appPayNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getInstallMent() {
		return installMent;
	}

	public void setInstallMent(String installMent) {
		this.installMent = installMent;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getAllNode() {
		return allNode;
	}

	public void setAllNode(String allNode) {
		this.allNode = allNode;
	}

	public String getNode() {
		return node;
	}

	public void setNode(String node) {
		this.node = node;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getProcessIndex() {
		return processIndex;
	}

	public void setProcessIndex(String processIndex) {
		this.processIndex = processIndex;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getFirstPayRate() {
		return firstPayRate;
	}

	public void setFirstPayRate(String firstPayRate) {
		this.firstPayRate = firstPayRate;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getFirstAmount() {
		return firstAmount;
	}

	public void setFirstAmount(String firstAmount) {
		this.firstAmount = firstAmount;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getTotalTerm() {
		return totalTerm;
	}

	public void setTotalTerm(String totalTerm) {
		this.totalTerm = totalTerm;
	}

	public List<RepayPlanEntity> getRepayPlans() {
		return repayPlans;
	}

	public void setRepayPlans(List<RepayPlanEntity> repayPlans) {
		this.repayPlans = repayPlans;
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

	public String getMthPayFee() {
		return mthPayFee;
	}

	public void setMthPayFee(String mthPayFee) {
		this.mthPayFee = mthPayFee;
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

	public String getSumprincipal() {
		return sumprincipal;
	}

	public void setSumprincipal(String sumprincipal) {
		this.sumprincipal = sumprincipal;
	}

	public String getTreatyUploadFlag() {
		return treatyUploadFlag;
	}

	public void setTreatyUploadFlag(String treatyUploadFlag) {
		this.treatyUploadFlag = treatyUploadFlag;
	}

	public String getTreatyUploadURL() {
		return treatyUploadURL;
	}

	public void setTreatyUploadURL(String treatyUploadURL) {
		this.treatyUploadURL = treatyUploadURL;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public String getTemAppPayNo() {
		return temAppPayNo;
	}

	public void setTemAppPayNo(String temAppPayNo) {
		this.temAppPayNo = temAppPayNo;
	}

	public String getAppProductId() {
		return appProductId;
	}

	public void setAppProductId(String appProductId) {
		this.appProductId = appProductId;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	
	
	

}
