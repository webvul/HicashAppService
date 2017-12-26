package com.hengyuan.hicash.entity.param;

import java.util.Date;

public class WhichPartRecord {

	private int id;
	private String createTime;
	//用户名
	private String userName;
	//代扣方
	private String whichPart;
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getWhichPart() {
		return whichPart;
	}
	public void setWhichPart(String whichPart) {
		this.whichPart = whichPart;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
