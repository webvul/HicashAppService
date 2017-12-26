package com.hengyuan.hicash.entity.app;

public class LoanProduct {

	private String name;
	private String minAmount;
	private String maxAmount;
	private String minInterestRate;
	private String maxInterestRate;
	private Integer period;
	private Integer tendDays;
	private String repayType;
	private String descp;
	private String imagePath;
	private Short checkDays;
	private String mthFeeType;
	private String mthFeeChargeWay;
	private String mthFeeAmt;
	private String mthFeeRate;
	private String minMthFee;
	private String maxMthFee;
	private String loanUnit;
	private String chargeType;
	private String chargeChargeWay;
	private String chargeAmt;
	private String chargeFeeRate;
	private String minChargeFee;
	
	private String maxChargeFee;

	private String creditInfo;

	private String custRate;

	// ---信贷产品增加----------------------------------------------

	/**
	 * 贷款产品名称
	 */
	private String creditName;

	/**
	 * 贷款金额
	 */
	private String creditMoney;

	/**
	 * 贷款利率
	 */
	private String creditRate;

	/**
	 * 还款期数
	 */
	private String creditPayTime;

	/**
	 * 月还款
	 */
	private String creditMonthPrice;

	/**
	 * 贷款产品类型
	 */
	private String creditProductType;

	/**
	 * 产品描述
	 */
	private String creditDesc;
	
	/**
	 * 期数
	 * @return
	 */
    private String creditday;
    /*
     * 资金方姓名
     * */
    private String inverstorName;
    
    private String industryCode;
   
    
    

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getLoanUnit() {
		return loanUnit;
	}

	public void setLoanUnit(String loanUnit) {
		this.loanUnit = loanUnit;
	}

	public String getInverstorName() {
		return inverstorName;
	}

	public void setInverstorName(String inverstorName) {
		this.inverstorName = inverstorName;
	}

	public String getCustRate() {
		return custRate;
	}

	public void setCustRate(String custRate) {
		this.custRate = custRate;
	}

	public LoanProduct() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMinAmount() {
		return minAmount;
	}

	public void setMinAmount(String minAmount) {
		this.minAmount = minAmount;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}

	public String getMinInterestRate() {
		return minInterestRate;
	}

	public void setMinInterestRate(String minInterestRate) {
		this.minInterestRate = minInterestRate;
	}

	public String getMaxInterestRate() {
		return maxInterestRate;
	}

	public void setMaxInterestRate(String maxInterestRate) {
		this.maxInterestRate = maxInterestRate;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Integer getTendDays() {
		return tendDays;
	}

	public void setTendDays(Integer tendDays) {
		this.tendDays = tendDays;
	}

	public String getRepayType() {
		return repayType;
	}

	public void setRepayType(String repayType) {
		this.repayType = repayType;
	}

	public String getDescp() {
		return descp;
	}

	public void setDescp(String descp) {
		this.descp = descp;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Short getCheckDays() {
		return checkDays;
	}

	public void setCheckDays(Short checkDays) {
		this.checkDays = checkDays;
	}

	public String getMthFeeType() {
		return mthFeeType;
	}

	public void setMthFeeType(String mthFeeType) {
		this.mthFeeType = mthFeeType;
	}

	public String getMthFeeChargeWay() {
		return mthFeeChargeWay;
	}

	public void setMthFeeChargeWay(String mthFeeChargeWay) {
		this.mthFeeChargeWay = mthFeeChargeWay;
	}

	public String getMthFeeAmt() {
		return mthFeeAmt;
	}

	public void setMthFeeAmt(String mthFeeAmt) {
		this.mthFeeAmt = mthFeeAmt;
	}

	public String getMthFeeRate() {
		return mthFeeRate;
	}

	public void setMthFeeRate(String mthFeeRate) {
		this.mthFeeRate = mthFeeRate;
	}

	public String getMinMthFee() {
		return minMthFee;
	}

	public void setMinMthFee(String minMthFee) {
		this.minMthFee = minMthFee;
	}

	public String getMaxMthFee() {
		return maxMthFee;
	}

	public void setMaxMthFee(String maxMthFee) {
		this.maxMthFee = maxMthFee;
	}

	public String getChargeType() {
		return chargeType;
	}

	public void setChargeType(String chargeType) {
		this.chargeType = chargeType;
	}

	public String getChargeChargeWay() {
		return chargeChargeWay;
	}

	public void setChargeChargeWay(String chargeChargeWay) {
		this.chargeChargeWay = chargeChargeWay;
	}

	public String getChargeAmt() {
		return chargeAmt;
	}

	public void setChargeAmt(String chargeAmt) {
		this.chargeAmt = chargeAmt;
	}

	public String getChargeFeeRate() {
		return chargeFeeRate;
	}

	public void setChargeFeeRate(String chargeFeeRate) {
		this.chargeFeeRate = chargeFeeRate;
	}

	public String getMinChargeFee() {
		return minChargeFee;
	}

	public void setMinChargeFee(String minChargeFee) {
		this.minChargeFee = minChargeFee;
	}

	public String getMaxChargeFee() {
		return maxChargeFee;
	}

	public void setMaxChargeFee(String maxChargeFee) {
		this.maxChargeFee = maxChargeFee;
	}

	public String getCreditName() {
		return creditName;
	}

	public void setCreditName(String creditName) {
		this.creditName = creditName;
	}

	public String getCreditMoney() {
		return creditMoney;
	}

	public void setCreditMoney(String creditMoney) {
		this.creditMoney = creditMoney;
	}

	public String getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}

	public String getCreditPayTime() {
		return creditPayTime;
	}

	public void setCreditPayTime(String creditPayTime) {
		this.creditPayTime = creditPayTime;
	}

	public String getCreditMonthPrice() {
		return creditMonthPrice;
	}

	public void setCreditMonthPrice(String creditMonthPrice) {
		this.creditMonthPrice = creditMonthPrice;
	}

	public String getCreditProductType() {
		return creditProductType;
	}

	public void setCreditProductType(String creditProductType) {
		this.creditProductType = creditProductType;
	}

	public String getCreditDesc() {
		return creditDesc;
	}

	public void setCreditDesc(String creditDesc) {
		this.creditDesc = creditDesc;
	}

	public String getCreditInfo() {
		return creditInfo;
	}

	public void setCreditInfo(String creditInfo) {
		this.creditInfo = creditInfo;
	}

	public String getCreditday() {
		return creditday;
	}

	public void setCreditday(String creditday) {
		this.creditday = creditday;
	}
    
}
