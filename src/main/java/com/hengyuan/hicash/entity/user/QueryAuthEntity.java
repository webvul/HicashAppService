package com.hengyuan.hicash.entity.user;

public class QueryAuthEntity {
	/*授信成功工作手机号*/
	private String mobile;
	/*授信成功次数*/
	private String count;
	/*授信最高额度¥50000*/
	private String maxAmount;

	/*预留司机账号*/
	private String  reserveNumber;
	
	/** 预留滴滴司机密码*/
	private String reservePassword;
	
	
	public String getReservePassword() {
		return reservePassword;
	}

	public void setReservePassword(String reservePassword) {
		this.reservePassword = reservePassword;
	}

	public String getReserveNumber() {
		return reserveNumber;
	}

	public void setReserveNumber(String reserveNumber) {
		this.reserveNumber = reserveNumber;
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

