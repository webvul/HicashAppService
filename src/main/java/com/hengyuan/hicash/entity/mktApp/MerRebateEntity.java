package com.hengyuan.hicash.entity.mktApp;

/**
 * 商户返点
 * 
 * @author Cary.Liu
 *
 */
public class MerRebateEntity {

	private boolean rebateFlag;

	private String merRpt;

	private String industryCode;

	private String supplierRpt;

	public boolean isRebateFlag() {
		return rebateFlag;
	}

	public void setRebateFlag(boolean rebateFlag) {
		this.rebateFlag = rebateFlag;
	}

	public String getMerRpt() {
		return merRpt;
	}

	public void setMerRpt(String merRpt) {
		this.merRpt = merRpt;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getSupplierRpt() {
		return supplierRpt;
	}

	public void setSupplierRpt(String supplierRpt) {
		this.supplierRpt = supplierRpt;
	}

}
