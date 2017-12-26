package com.hengyuan.hicash.entity.user;

/**
 * 微信红包实体类
 * @author tan
 *
 */
public class WxbagEntity {
   
   /* 红包自增id*/
   private int bagId;
   
   /* 用户id号*/
   private String userId;
   
   /* 进件订单号*/
   private String userOrdersn;
   
   /* 红包分组1.注册 2.进件*/
   private String eventGroup;
   
   /* 活动唯一关键字*/
   private String evenKey;
   
   /* 创建时间*/
   private String cTime;
   
   /* 是否需要发放红包 默认0*/
   private int is_pay_money;
   
   private String userPhone;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserOrdersn() {
		return userOrdersn;
	}

	public void setUserOrdersn(String userOrdersn) {
		this.userOrdersn = userOrdersn;
	}

	public String getEvenKey() {
		return evenKey;
	}

	public void setEvenKey(String evenKey) {
		this.evenKey = evenKey;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public int getBagId() {
		return bagId;
	}

	public void setBagId(int bagId) {
		this.bagId = bagId;
	}

	public String getEventGroup() {
		return eventGroup;
	}

	public void setEventGroup(String eventGroup) {
		this.eventGroup = eventGroup;
	}

	public int getIs_pay_money() {
		return is_pay_money;
	}

	public void setIs_pay_money(int is_pay_money) {
		this.is_pay_money = is_pay_money;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
   
	   
	
}
