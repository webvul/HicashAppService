package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class LoanPayResp extends ParmResponse {

	private String lowRate;// 低月利率
	private String highRate;// 高月利率
	private String lowPay;// 低本息
	private String highPay;// 高本息

	public String getLowRate() {
		return lowRate;
	}

	public void setLowRate(String lowRate) {
		this.lowRate = lowRate;
	}

	public String getHighRate() {
		return highRate;
	}

	public void setHighRate(String highRate) {
		this.highRate = highRate;
	}

	public String getLowPay() {
		return lowPay;
	}

	public void setLowPay(String lowPay) {
		this.lowPay = lowPay;
	}

	public String getHighPay() {
		return highPay;
	}

	public void setHighPay(String highPay) {
		this.highPay = highPay;
	}

}
