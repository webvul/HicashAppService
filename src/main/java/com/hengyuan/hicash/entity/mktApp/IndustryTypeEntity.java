package com.hengyuan.hicash.entity.mktApp;

/**
 * 行业实体
 * 
 * @author Cary.Liu
 *
 */
public class IndustryTypeEntity {

	/** 行业代码 */
	private String industryCode;

	/** 行业名称 */
	private String industryName;

	/** 客户类型 */
	private String custType;

	/** 申请类型 */
	private String payType;

	/** 上传模板 */
	private String uploadModel;

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

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

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getUploadModel() {
		return uploadModel;
	}

	public void setUploadModel(String uploadModel) {
		this.uploadModel = uploadModel;
	}

}
