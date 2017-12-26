package com.hengyuan.hicash.entity.app;

/**
 * 账户首页申请件展示
 * 
 * @author Andy.Niu
 * @create 2014-08-16
 */
public class AccountPayEntity {

	/** 流水号 */
	private String appPayNo;

	/** 产品ID */
	private String productId;

	/** 商户商品ID */
	private String merProId;

	/** 信贷产品ID */
	private String loanProduct;

	/** 申请产品名称 */
	private String productName;

	/** 行业代码 */
	private String industryCode;

	/** 申请类别 NORMAL OR CASH */
	private String applyType;

	/** 还款总期数 */
	private String installMent;

	/** 申请时间（yyyy-MM-dd）格式 */
	private String applyDate;

	/** 借款金额 */
	private String loanAmount;

	/** 借款金额 */
	private String applyAmount;

	/** 大节点 */
	private String allNode;

	/** 节点 */
	private String node;

	/** 状态 */
	private String status;

	private String custType;

	/** 每月还款金额 */
	private String mthPayFee;

	/** 支付首付比率 */
	private String firstPayRate;

	/** 首付金额 */
	private String firstAmount;

	private String createDate;

	private String treatyUploadFlag;

	private String treatyUploadURL;

	/** 审批驳回原因 */
	private String rejectDesc;

	/** 销售凭证驳回原因 */
	private String appcheckdesc;

	/** 上传协议（驳回）原因 */
	private String rejectCause;
	
	/** 商品ID */
	private String appProductId;

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

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
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

	public String getMthPayFee() {
		return mthPayFee;
	}

	public void setMthPayFee(String mthPayFee) {
		this.mthPayFee = mthPayFee;
	}

	public String getFirstPayRate() {
		return firstPayRate;
	}

	public void setFirstPayRate(String firstPayRate) {
		this.firstPayRate = firstPayRate;
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

	public String getAppProductId() {
		return appProductId;
	}

	public void setAppProductId(String appProductId) {
		this.appProductId = appProductId;
	}

}
