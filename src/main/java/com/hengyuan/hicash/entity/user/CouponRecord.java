package com.hengyuan.hicash.entity.user;

public class CouponRecord {

	private String coupon_record_id;

	private String coupon_code_id;

	private String user_id;

	private String order_id;

	private String add_time;

	private String aux_info;

	public String getCoupon_record_id() {
		return coupon_record_id;
	}

	public void setCoupon_record_id(String coupon_record_id) {
		this.coupon_record_id = coupon_record_id;
	}

	public String getCoupon_code_id() {
		return coupon_code_id;
	}

	public void setCoupon_code_id(String coupon_code_id) {
		this.coupon_code_id = coupon_code_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOrder_id() {
		return order_id;
	}

	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getAdd_time() {
		return add_time;
	}

	public void setAdd_time(String add_time) {
		this.add_time = add_time;
	}

	public String getAux_info() {
		return aux_info;
	}

	public void setAux_info(String aux_info) {
		this.aux_info = aux_info;
	}

}
