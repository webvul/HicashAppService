package com.hengyuan.hicash.entity.notic;



public class NoticeSendflow  {
	
	private String id;

	private String username;
	private String bussNo;
	private String template;
	private String content;
	private String ntype;
	private String receiveTelphone;
	private String receiveEmail;
	private String sendTime;
	private String sendStatus;//WAIT\SUCC\FAIL
	private String operator;
	private String sendFlag;
	private String CreateTime;
	
	private String title;

	public NoticeSendflow() {
	}

	public NoticeSendflow(String username) {
		this.username = username;
	}
	public NoticeSendflow(String username, String bussNo, String template,
			String content, String ntype, String receiveTelphone, String receiveEmail, String sendTime,
			String sendStatus, String operator, String sendFlag) {
		this.username = username;
		this.bussNo = bussNo;
		this.template = template;
		this.content = content;
		this.ntype = ntype;
		this.receiveTelphone = receiveTelphone;
		this.receiveEmail = receiveEmail;
		this.sendTime = sendTime;
		this.sendStatus = sendStatus;
		this.operator = operator;
		this.sendFlag = sendFlag;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBussNo() {
		return bussNo;
	}

	public void setBussNo(String bussNo) {
		this.bussNo = bussNo;
	}

	public String getTemplate() {
		return template;
	}

	public void setTemplate(String template) {
		this.template = template;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNtype() {
		return ntype;
	}

	public void setNtype(String ntype) {
		this.ntype = ntype;
	}

	public String getReceiveTelphone() {
		return receiveTelphone;
	}

	public void setReceiveTelphone(String receiveTelphone) {
		this.receiveTelphone = receiveTelphone;
	}

	public String getReceiveEmail() {
		return receiveEmail;
	}

	public void setReceiveEmail(String receiveEmail) {
		this.receiveEmail = receiveEmail;
	}

	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSendStatus() {
		return sendStatus;
	}

	public void setSendStatus(String sendStatus) {
		this.sendStatus = sendStatus;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getSendFlag() {
		return sendFlag;
	}

	public void setSendFlag(String sendFlag) {
		this.sendFlag = sendFlag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
	
}
