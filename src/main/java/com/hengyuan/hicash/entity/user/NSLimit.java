package com.hengyuan.hicash.entity.user;

/**
 * 女神额度查询
 * @author ding
 *
 */
public class NSLimit {

	private String username;
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
