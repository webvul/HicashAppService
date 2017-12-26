package com.hengyuan.hicash.entity.user;

public class FaceValConfigEntity {
	private String updateTime; // username
	private String updateUser;// 临时单号
	private String whichPart;// 识别机构：fst：易道，sec：face++,易道和face++：fstsec
	private String createTime;// 
	private String createUser;// 
	
	private String fstCount;//识别结构fst次数
	private String secCount;//识别机构sec次数
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public String getWhichPart() {
		return whichPart;
	}
	public void setWhichPart(String whichPart) {
		this.whichPart = whichPart;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getFstCount() {
		return fstCount;
	}
	public void setFstCount(String fstCount) {
		this.fstCount = fstCount;
	}
	public String getSecCount() {
		return secCount;
	}
	public void setSecCount(String secCount) {
		this.secCount = secCount;
	}
	
	
	
}
