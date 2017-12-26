package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class SaveMyMessageReq extends RequestSequence{
	
	private static final long serialVersionUID = 1L;
	
	/** 消息Code */
	private String code;
	
	/** 用户名*/
	private String userName;

	/** 手机号 */
	private String mobile;

	/** 身份证号*/
	private String idNo;

	/** 姓名 */
	private String name;
	
	/**是否已读:1/已读,0/未读*/
	private String isRead;

	/** 标题 */
	private String title;

	/** 消息内容*/
	private String content;
	
	/** 功能 */
	private String operate;
	
	/** 消息类型:待定*/
	private String type;

	/** 创建时间 */
	private String createTime;

	/** 修改时间*/
	private String updateTime;
	
	/** 流水号 */
	private String appNo;

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	
	
}
