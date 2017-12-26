package com.hengyuan.hicash.entity.user;

/**
 * 
 * @author teng
 *
 */
public class DdsjLimit {

	private String username;
	private String sx_mobile;
	private String sx_mobile_password;
	private String amount;
	private String latelySx_success_date;
	private String latelyTe_success_date;
	private String sx_success_time;
	private String is_credit;
	private String status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSx_mobile() {
		return sx_mobile;
	}

	public void setSx_mobile(String sx_mobile) {
		this.sx_mobile = sx_mobile;
	}

	public String getSx_mobile_password() {
		return sx_mobile_password;
	}

	public void setSx_mobile_password(String sx_mobile_password) {
		this.sx_mobile_password = sx_mobile_password;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getLatelySx_success_date() {
		return latelySx_success_date;
	}

	public void setLatelySx_success_date(String latelySx_success_date) {
		this.latelySx_success_date = latelySx_success_date;
	}

	public String getLatelyTe_success_date() {
		return latelyTe_success_date;
	}

	public void setLatelyTe_success_date(String latelyTe_success_date) {
		this.latelyTe_success_date = latelyTe_success_date;
	}

	public String getSx_success_time() {
		return sx_success_time;
	}

	public void setSx_success_time(String sx_success_time) {
		this.sx_success_time = sx_success_time;
	}

	public String getIs_credit() {
		return is_credit;
	}

	public void setIs_credit(String is_credit) {
		this.is_credit = is_credit;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
