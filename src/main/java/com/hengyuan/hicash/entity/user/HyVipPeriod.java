package com.hengyuan.hicash.entity.user;

import java.util.Date;


/** 
 * @author meng.zhang 
 * 2017年2月22日 下午5:42:57
 * 类说明 
 */
public class HyVipPeriod {
	
	private Integer id;
	
	/** 用户名*/
	private String username;
	
	/** 期数*/
	private Integer period;
	
	/** 创建日期*/
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
