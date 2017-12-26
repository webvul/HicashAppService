package com.hengyuan.hicash.entity.user;
/**
 * 优惠券明细
 * @author Administrator
 *
 */
public class CouPonCodeEntity {
	
	//明细id
	private String couponCodeId;
	//优惠券id
	private String couponId;
	//优惠券code
	private String couponCode;
	//是否启用
	private String isEnable;

	public String getCouponCodeId() {
		return couponCodeId;
	}

	public void setCouponCodeId(String couponCodeId) {
		this.couponCodeId = couponCodeId;
	}

	public String getCouponId() {
		return couponId;
	}

	public void setCouponId(String couponId) {
		this.couponId = couponId;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}
	
	

}
