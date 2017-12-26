package com.hengyuan.hicash.entity.user;
/**
 * 优惠券使用记录
 * @author Administrator
 *
 */
public class CouPonRecordEntity {
	
	private String couponRecordId;
	//优惠明细id
	private String couponCodeId;
	//用户id
	private String userId;
	//订单id
	private String orderId;
	//使用时间
	private String addTime;
	public String getCouponRecordId() {
		return couponRecordId;
	}
	public void setCouponRecordId(String couponRecordId) {
		this.couponRecordId = couponRecordId;
	}
	public String getCouponCodeId() {
		return couponCodeId;
	}
	public void setCouponCodeId(String couponCodeId) {
		this.couponCodeId = couponCodeId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	
	

}
