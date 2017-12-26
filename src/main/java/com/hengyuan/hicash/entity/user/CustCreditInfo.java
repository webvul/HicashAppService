package com.hengyuan.hicash.entity.user;

/**
 * 白领保存资信信息实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-24
 * @table cust_credit_info
 */
public class CustCreditInfo {

	private int id;
	private String username;//
	private String monthlyIncome;// monthly_income 当前月收入
	private String monthlyConsumption;// monthlyConsumption 当前月消费
	private String monthRent;// monthRent 房租月供
	private String loanTotalAmount;// loanTotalAmount 贷款总额
	private String loanCount;// loanCount 贷款数量
	private String loanMonthRent;// loanMonthRent 贷款月供
	private String creditCardCount;// creditCardCount 信用卡数量
	private String creditHigthAmount;// creditHigthAmount 信用卡最高额度
	private String creditTotalAmount;// creditTotalAmount 信用卡总额度

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	public String getMonthlyConsumption() {
		return monthlyConsumption;
	}

	public void setMonthlyConsumption(String monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
	}

	public String getMonthRent() {
		return monthRent;
	}

	public void setMonthRent(String monthRent) {
		this.monthRent = monthRent;
	}

	public String getLoanTotalAmount() {
		return loanTotalAmount;
	}

	public void setLoanTotalAmount(String loanTotalAmount) {
		this.loanTotalAmount = loanTotalAmount;
	}

	public String getLoanCount() {
		return loanCount;
	}

	public void setLoanCount(String loanCount) {
		this.loanCount = loanCount;
	}

	public String getLoanMonthRent() {
		return loanMonthRent;
	}

	public void setLoanMonthRent(String loanMonthRent) {
		this.loanMonthRent = loanMonthRent;
	}

	public String getCreditCardCount() {
		return creditCardCount;
	}

	public void setCreditCardCount(String creditCardCount) {
		this.creditCardCount = creditCardCount;
	}

	public String getCreditHigthAmount() {
		return creditHigthAmount;
	}

	public void setCreditHigthAmount(String creditHigthAmount) {
		this.creditHigthAmount = creditHigthAmount;
	}

	public String getCreditTotalAmount() {
		return creditTotalAmount;
	}

	public void setCreditTotalAmount(String creditTotalAmount) {
		this.creditTotalAmount = creditTotalAmount;
	}

}
