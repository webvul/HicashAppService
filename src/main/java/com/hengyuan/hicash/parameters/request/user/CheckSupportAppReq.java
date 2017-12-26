package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class CheckSupportAppReq extends RequestSequence {

	private static final long serialVersionUID = 5486690404923696137L;
	/** 行业 */
	private String industryCode; 
	/** 客户类型 */
	private String custType; 


	/** 手机号*/
	private String mobile;
	
	/** 是否是信用卡 Y/N*/
	private String is_type;
	private String user_name;
	
	/** 期数*/
	private String periods;
	
	/** 申请金额*/
	private String tranPrice;
	
	/** 申请来源*/
	private String applyFrom;//H5;2017/06/07因：滴滴贷和秒贷3期的，如果是H5申请，则拒绝
	
	/** 信贷id*/
	private String loanProductId;

	public String getPeriods() {
		return periods;
	}

	public void setPeriods(String periods) {
		this.periods = periods;
	}

	public String getTranPrice() {
		return tranPrice;
	}

	public void setTranPrice(String tranPrice) {
		this.tranPrice = tranPrice;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIs_type() {
		return is_type;
	}

	public void setIs_type(String is_type) {
		this.is_type = is_type;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getApplyFrom() {
		return applyFrom;
	}

	public void setApplyFrom(String applyFrom) {
		this.applyFrom = applyFrom;
	}

	public String getLoanProductId() {
		return loanProductId;
	}

	public void setLoanProductId(String loanProductId) {
		this.loanProductId = loanProductId;
	}
	
	
}
