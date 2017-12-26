package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.user.QueryAuthEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

public class QueryAuthResp extends ParmResponse{
		
	/* 授信成功工作手机号*/
	private String mobile;
	/*授信成功次数*/
	private String count;
	/*授信最高额度¥50000*/
	private String maxAmount;
	/*是否可编辑:Y可编辑,N不可编辑*/
	private String isEdit;
	
	/*预留司机账号*/
	private String  reserveNumber;
	
	/* 预留司机密码*/
	private String reservePassword;
	
	private String isRequired; //是否必填
	
	
	

	public String getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(String isRequired) {
		this.isRequired = isRequired;
	}

	public String getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(String reserveNumber) {
		this.reserveNumber = reserveNumber;
	}

	public String getReservePassword() {
		return reservePassword;
	}

	public void setReservePassword(String reservePassword) {
		this.reservePassword = reservePassword;
	}

	public String getIsEdit() {
		return isEdit;
	}

	public void setIsEdit(String isEdit) {
		this.isEdit = isEdit;
	}

	public String getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(String maxAmount) {
		this.maxAmount = maxAmount;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}
	
}
