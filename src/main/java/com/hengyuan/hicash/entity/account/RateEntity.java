package com.hengyuan.hicash.entity.account;

public class RateEntity {

	private String serverRate;// 服务费率

	private String creditRate;// 贷款利率

	private String mouthRate;// 月管理费率

	private String installments; // 期数

	private String creditDay; // 贷款天数

	private String investorUsername;// 投资人

	public String getInvestorUsername() {
		return investorUsername;
	}

	public void setInvestorUsername(String investorUsername) {
		this.investorUsername = investorUsername;
	}

	public String getInstallments() {
		return installments;
	}

	public void setInstallments(String installments) {
		this.installments = installments;
	}

	public String getServerRate() {
		return serverRate;
	}

	public void setServerRate(String serverRate) {
		this.serverRate = serverRate;
	}

	public String getCreditRate() {
		return creditRate;
	}

	public void setCreditRate(String creditRate) {
		this.creditRate = creditRate;
	}

	public String getMouthRate() {
		return mouthRate;
	}

	public void setMouthRate(String mouthRate) {
		this.mouthRate = mouthRate;
	}

	public String getCreditDay() {
		return creditDay;
	}

	public void setCreditDay(String creditDay) {
		this.creditDay = creditDay;
	}

	public RateEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RateEntity(String serverRate, String creditRate, String mouthRate, String installments, String creditDay,
			String investorUsername) {
		super();
		this.serverRate = serverRate;
		this.creditRate = creditRate;
		this.mouthRate = mouthRate;
		this.installments = installments;
		this.creditDay = creditDay;
		this.investorUsername = investorUsername;
	}

}
