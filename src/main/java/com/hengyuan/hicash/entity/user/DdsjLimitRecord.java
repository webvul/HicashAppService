package com.hengyuan.hicash.entity.user;


public class DdsjLimitRecord {
	/*用户名*/
	private String username;
	/*现有额度*/
	private Double amount;
	/*原有额度*/
	private Double old_amount;
	/*授信类型 SX：授信 TE:提额*/
	private String type;
	/*创建时间*/
	private String create_time;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public Double getOld_amount() {
		return old_amount;
	}
	public void setOld_amount(Double old_amount) {
		this.old_amount = old_amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	
	
}
