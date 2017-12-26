package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 创建申请件 创建蓝领数码申请
 * 
 * @author LiHua.Ren
 * @createDate 2016-01-25
 * */

public class CreateAppBlueCollarReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 行业代码 */
	private String industryCode;

	/** 客户用户名 */
	private String userName;

	/** 业务员用户名 */
//	private String approveUser;

	/** 交易金额 */
	private String tranPrice;

	/** 申请方式 */
	private String applyType;

	/** 客户类型 */
	private String custType;

	/** 首付比率 */
	private String firstRate;

	/** 贷款产品ID */
	private String loanProduct;

	/** 产品名称 */
	private String productName;

	/** 产品规格参数 PRODUCT_DETAIL */
	private String productDetail;

	/** 省份代码 */
	private String province;

	/** 城市代码 */
	private String city;

	/** 开户行对应数据库ID */
	private String bankNo;

	/** 商户ID */
	private String supplierId;

	/** 售点ID */
	private String siteId;

	/** 商户输入商品总价 */
	private String merProPrice;

	/** 商户产品ID */
	private String merProId;

	/** 借款用途 */
	private String applyUse;

	/** 走嗨商贷等聚信立流程 */
	private String createFlag;

	/** 申请来源 DASH：DASHBOARD申请 */
	private String applyFrom;
	
	/** 借款用途 */
	private String loanUse;

	public String getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
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

//	public String getApproveUser() {
//		return approveUser;
//	}
//
//	public void setApproveUser(String approveUser) {
//		this.approveUser = approveUser;
//	}

	public String getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
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

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDetail() {
		return productDetail;
	}

	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getMerProPrice() {
		return merProPrice;
	}

	public void setMerProPrice(String merProPrice) {
		this.merProPrice = merProPrice;
	}

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getApplyUse() {
		return applyUse;
	}

	public void setApplyUse(String applyUse) {
		this.applyUse = applyUse;
	}

	public String getCreateFlag() {
		return createFlag;
	}

	public void setCreateFlag(String createFlag) {
		this.createFlag = createFlag;
	}

	public String getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}

}
