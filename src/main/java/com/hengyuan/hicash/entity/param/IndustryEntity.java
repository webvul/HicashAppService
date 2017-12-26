package com.hengyuan.hicash.entity.param;

/**
 * 行业
 * 
 * @author Cary.Liu
 *
 */
public class IndustryEntity {

	/** 行业代码 */
	private String industryCode;

	/** 行业名称 */
	private String industryName;

	/** 行业逻辑代码 */
	private String industryLogicCode;

	/** 行业父逻辑代码 */
	private String industryPrtLogicCode;

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getIndustryLogicCode() {
		return industryLogicCode;
	}

	public void setIndustryLogicCode(String industryLogicCode) {
		this.industryLogicCode = industryLogicCode;
	}

	public String getIndustryPrtLogicCode() {
		return industryPrtLogicCode;
	}

	public void setIndustryPrtLogicCode(String industryPrtLogicCode) {
		this.industryPrtLogicCode = industryPrtLogicCode;
	}

}
