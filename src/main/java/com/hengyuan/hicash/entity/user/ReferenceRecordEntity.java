package com.hengyuan.hicash.entity.user;

/**
 * 统计推荐人信息接口
 * 
 * @author lihua.ren
 * @createDate 2015-10-13
 *
 */
public class ReferenceRecordEntity {
	/** 推荐人 */
	private String reference;
	/** 被推荐人 */
	private String recommend;
	/** 推荐时间 */
	private String referenceTime;
	/** 推荐业务：申请，注册 */
	private String bussiness;
	/** 来源页面 */
	private String upPage;
	/** ip */
	private String referenceIP;
	/** 申请单号 */
	private String appNo;
	/** 行业 */
	private String  hyIndustryCode;
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
	/**
	 * @return the recommend
	 */
	public String getRecommend() {
		return recommend;
	}
	/**
	 * @param recommend the recommend to set
	 */
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}
	/**
	 * @return the referenceTime
	 */
	public String getReferenceTime() {
		return referenceTime;
	}
	/**
	 * @param referenceTime the referenceTime to set
	 */
	public void setReferenceTime(String referenceTime) {
		this.referenceTime = referenceTime;
	}
	/**
	 * @return the bussiness
	 */
	public String getBussiness() {
		return bussiness;
	}
	/**
	 * @param bussiness the bussiness to set
	 */
	public void setBussiness(String bussiness) {
		this.bussiness = bussiness;
	}
	/**
	 * @return the upPage
	 */
	public String getUpPage() {
		return upPage;
	}
	/**
	 * @param upPage the upPage to set
	 */
	public void setUpPage(String upPage) {
		this.upPage = upPage;
	}
	/**
	 * @return the referenceIP
	 */
	public String getReferenceIP() {
		return referenceIP;
	}
	/**
	 * @param referenceIP the referenceIP to set
	 */
	public void setReferenceIP(String referenceIP) {
		this.referenceIP = referenceIP;
	}
	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}
	/**
	 * @param appNo the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	/**
	 * @return the hyIndustryCode
	 */
	public String getHyIndustryCode() {
		return hyIndustryCode;
	}
	/**
	 * @param hyIndustryCode the hyIndustryCode to set
	 */
	public void setHyIndustryCode(String hyIndustryCode) {
		this.hyIndustryCode = hyIndustryCode;
	}

}
