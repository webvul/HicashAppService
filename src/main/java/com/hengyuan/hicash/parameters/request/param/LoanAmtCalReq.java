package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * 贷款金额计算接收参数
 * 
 * @author Andy.Niu
 * @create date 2014-07-23
 */
public class LoanAmtCalReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 客户类型 学生 or 白领 */
	private String custType;

	/** 城市代码 6位数字编码，默认6个0 */
	private String cityCode;

	/** 产品类型 CASH OR NORMAL */
	private String payType;

	/** 成交价格 */
	private String tranPrice;

	/** 首付比率 */
	private String firstRate;

	/** 贷款产品ID */
	private String loanProduct;

	/** 实物产品ID */
	private String productId;

	/** 商户产品ID(不收取服务费) */
	private String merProId;

	/** 商户id */
	private String supplierId;

	/** 行业代码 */
	private String industryCode;

	/** 商户APP测试字段 */
	private String merTestParam;
	
	/** 售点ID */
	private String saleSiteId;
	
	/**投资人*/
	private String  investorName; 
	
	/**区域(省份)*/
	private String createProvince; 
	
	
	private String userName;

	
	/*---------------------2016-10-18针对车抵分期增加字段*/
	/**还款方式:DEBX-等额本息  XXHB-先息后本*/
	private String repayType;
	
	/** 费率 */
	private String rate;
	
	

	public String getMerTestParam() {
		return merTestParam;
	}

	public void setMerTestParam(String merTestParam) {
		this.merTestParam = merTestParam;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
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
	

	public String getSaleSiteId() {
		return saleSiteId;
	}

	public void setSaleSiteId(String saleSiteId) {
		this.saleSiteId = saleSiteId;
	}

	public String getInvestorName() {
		return investorName;
	}

	public void setInvestorName(String investorName) {
		this.investorName = investorName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCreateProvince() {
		return createProvince;
	}

	public void setCreateProvince(String createProvince) {
		this.createProvince = createProvince;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


}
