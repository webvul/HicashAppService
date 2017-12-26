package com.hengyuan.hicash.entity.user;



public class CustLetter  {

	private String username;
	private String sendUser;
	private String letterTitile;
	private String letterContent;
	private Boolean status;
	private String letterType;
	
	private String createTime;

	public CustLetter() {
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getSendUser() {
		return sendUser;
	}

	public void setSendUser(String sendUser) {
		this.sendUser = sendUser;
	}

	public String getLetterTitile() {
		return letterTitile;
	}

	public void setLetterTitile(String letterTitile) {
		this.letterTitile = letterTitile;
	}

	public String getLetterContent() {
		return letterContent;
	}

	public void setLetterContent(String letterContent) {
		this.letterContent = letterContent;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getLetterType() {
		return letterType;
	}

	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	
	
}
