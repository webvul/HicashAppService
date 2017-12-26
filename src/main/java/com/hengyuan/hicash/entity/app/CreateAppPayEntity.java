package com.hengyuan.hicash.entity.app;

/**
 * 初始化创建申请件对象
 * 
 * @author Andy.Niu
 * @create 2014-08-06
 */
public class CreateAppPayEntity {

	/* 申请信息类 */
	/** 流水号 */
	private String appPayNo;

	/** 产品ID */
	private String productId;

	/** 产品类型 */
	private String productType;

	/** 产品名称 */
	private String productName;

	/** loanProductId */
	private String loanProductId;

	/** 申请类型 */
	private String payType;

	/** 客户类型 */
	private String custType;

	/* 用户类 */
	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 身份证号 */
	private String identityNo;

	/** 手机号码 */
	private String mobileNo;

	/** 学校ID */
	private String schoolId;

	/** 学校名称 */
	private String applyAddress;

	/* 金额类 */
	/** 成交价 */
	private String tranPrice;

	/** 分期期数 */
	private String installMent;

	/** 月还款 */
	private String monthPay;

	/** 申请折扣 */
	private String disCount;

	/** 申请金额 */
	private String applyAmount;

	/** 首付比率 */
	private String firstRate;

	/** 首付金额 */
	private String firstPayMent;

	/** 首付中含有的本金 */
	private String firstPrin;

	/* 标志类 */
	/** 催收是否已分配 */
	private String isAllocation;

	/** 是否已经选择商户 */
	private String isMerchant;

	/* 省份类 */
	/** 省份代码 */
	private String provice;

	/** 城市代码 */
	private String cityCode;

	/** 区域代码 */
	private String areaCode;

	/** 销售代码 */
	private String saleCode;

	/** 销售员用户名 */
	private String saleServer;

	/* 状态类 */

	/** 大节点 */
	private String allNode;

	/** 节点 */
	private String node;

	/** 状态 */
	private String status;

	private String createDate;

	private String createUser;
	/**推荐人*/
	private String reference;

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getAppPayNo() {
		return appPayNo;
	}

	public void setAppPayNo(String appPayNo) {
		this.appPayNo = appPayNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSaleCode() {
		return saleCode;
	}

	public void setSaleCode(String saleCode) {
		this.saleCode = saleCode;
	}

	public String getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}

	public String getInstallMent() {
		return installMent;
	}

	public void setInstallMent(String installMent) {
		this.installMent = installMent;
	}

	public String getMonthPay() {
		return monthPay;
	}

	public void setMonthPay(String monthPay) {
		this.monthPay = monthPay;
	}

	public String getDisCount() {
		return disCount;
	}

	public void setDisCount(String disCount) {
		this.disCount = disCount;
	}

	public String getIsAllocation() {
		return isAllocation;
	}

	public void setIsAllocation(String isAllocation) {
		this.isAllocation = isAllocation;
	}

	public String getIsMerchant() {
		return isMerchant;
	}

	public void setIsMerchant(String isMerchant) {
		this.isMerchant = isMerchant;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProvice() {
		return provice;
	}

	public void setProvice(String provice) {
		this.provice = provice;
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

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
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

	public String getFirstRate() {
		return firstRate;
	}

	public void setFirstRate(String firstRate) {
		this.firstRate = firstRate;
	}

	public String getFirstPayMent() {
		return firstPayMent;
	}

	public void setFirstPayMent(String firstPayMent) {
		this.firstPayMent = firstPayMent;
	}

	public String getFirstPrin() {
		return firstPrin;
	}

	public void setFirstPrin(String firstPrin) {
		this.firstPrin = firstPrin;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getSchoolId() {
		return schoolId;
	}

	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}

	public String getApplyAddress() {
		return applyAddress;
	}

	public void setApplyAddress(String applyAddress) {
		this.applyAddress = applyAddress;
	}

	public String getSaleServer() {
		return saleServer;
	}

	public void setSaleServer(String saleServer) {
		this.saleServer = saleServer;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the reference
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

}
