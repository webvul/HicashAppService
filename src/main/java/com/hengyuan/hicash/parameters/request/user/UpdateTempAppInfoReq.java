package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/** 
 * @author dong.liu 
 * 2017-3-2 下午2:17:15
 * 类说明 
 */
public class UpdateTempAppInfoReq extends RequestSequence{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 临时订单号
	 */
	private String tempAppNo;

	
	/**
	 * 申请件流程状态
	 */
	private String validateType;
	

	
	/**
	 * 申请设备
	 */
	private String applyFrom;
	/**
	 * 行业
	 */
	private String industryCode;
	
	/**
	 * 节点
	 */
	private String node;
	
	/**
	 * 状态
	 */
	private String status;
	
	/**
	 * 身份
	 */
	private String custType;

	

	/**
	 * 申请件流程状态副本
	 */
	private String validateTypea;

	
	
	


	public String getValidateTypea() {
		return validateTypea;
	}

	public void setValidateTypea(String validateTypea) {
		this.validateTypea = validateTypea;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
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

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}
	
	
	
	
	
	
	
	
	

}
