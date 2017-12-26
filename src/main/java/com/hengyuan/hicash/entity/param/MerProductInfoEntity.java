package com.hengyuan.hicash.entity.param;

public class MerProductInfoEntity {

	/** 商户产品ID */
	private String merProId;

	/** 商户产品名称 */
	private String merProName;

	/** 该产品是否返点 */
	private String isRebate;

	/** 产品返点比例 */
	private String merProductRPT;

	/** 商户返点比例 */
	private String supplierRPT;

	/** 行业代码 */
	private String industryCode;

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getMerProName() {
		return merProName;
	}

	public void setMerProName(String merProName) {
		this.merProName = merProName;
	}

	public String getIsRebate() {
		return isRebate;
	}

	public void setIsRebate(String isRebate) {
		this.isRebate = isRebate;
	}

	public String getMerProductRPT() {
		return merProductRPT;
	}

	public void setMerProductRPT(String merProductRPT) {
		this.merProductRPT = merProductRPT;
	}

	public String getSupplierRPT() {
		return supplierRPT;
	}

	public void setSupplierRPT(String supplierRPT) {
		this.supplierRPT = supplierRPT;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

}
