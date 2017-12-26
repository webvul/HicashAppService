package com.hengyuan.hicash.entity.app;

/**
 * 申请件查询实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 */
public class ApplicationEntity {

	/** 调用远程服务时用来接收响应结果 */
	private String uuid;

	/** 申请件单号 */
	private String appNo;

	/** 月还款 */
	private String monthPay;

	/** 还款期数 */
	private String installMent;

	/** 城市 */
	private String cityCode;

	/** 产品类型 */
	private String payType;

	/** 成交金额 */
	private String tranPrice;

	/** 首付比率 */
	private String firstRate;

	/** 信贷产品ID */
	private String loanProduct;

	/** 实物产品ID */
	private String productId;

	/** 是否短信通知过业务员 */
	private String sendApproFlag;

	/** 售点ID */
	private String siteCode;

	/** 客户真实姓名 */
	private String appRealName;
    /**用户名**/
	private  String appUsername;
	/** 产品名称 */
	private String productName;

	private String custType;

	private String createDate;

	/** 申请金额 */
	private String applyAmount;

	private String allnode;

	private String node;

	private String status;

	private String industryCode;
	
	//进件来源
	private String applyFrom;

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getAllnode() {
		return allnode;
	}

	public void setAllnode(String allnode) {
		this.allnode = allnode;
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

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getAppRealName() {
		return appRealName;
	}

	public void setAppRealName(String appRealName) {
		this.appRealName = appRealName;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSendApproFlag() {
		return sendApproFlag;
	}

	public void setSendApproFlag(String sendApproFlag) {
		this.sendApproFlag = sendApproFlag;
	}

	public String getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(String monthPay) {
		this.monthPay = monthPay;
	}

	public String getInstallMent() {
		return installMent;
	}

	public void setInstallMent(String installMent) {
		this.installMent = installMent;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}

	public String getFirstRate() {
		return firstRate;
	}

	public void setFirstRate(String firstRate) {
		this.firstRate = firstRate;
	}

	public String getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(String loanProduct) {
		this.loanProduct = loanProduct;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getAppUsername() {
		return appUsername;
	}

	public void setAppUsername(String appUsername) {
		this.appUsername = appUsername;
	}
	
	public String getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}

}
