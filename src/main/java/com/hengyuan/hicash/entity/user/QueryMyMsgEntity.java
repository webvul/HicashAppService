package com.hengyuan.hicash.entity.user;

import java.util.List;

public class QueryMyMsgEntity {

	/*响应消息*/
	private String id;
	
	/*消息标题*/
	private String title;
	
	/*消息内容*/
	private String content;
	
	/*状态:1/已读,0/未读*/
	private String is_read;
	
	/*消息类型*/
	private String type;
	
	/*功能*/
	private String operate;
	
	/*创建时间*/
	private String createTime;

	/*商品分类id*/
	private String categoryId;
	
	/*流水号*/
	private String appNo;
	
	/*是否取消： Y/是 ,N/否*/
	private String isCancel;

	public String getIsCancel() {
		return isCancel;
	}

	public void setIsCancel(String isCancel) {
		this.isCancel = isCancel;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getIs_read() {
		return is_read;
	}

	public void setIs_read(String is_read) {
		this.is_read = is_read;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

}
