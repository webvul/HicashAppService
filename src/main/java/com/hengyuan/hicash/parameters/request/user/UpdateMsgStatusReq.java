package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

public class UpdateMsgStatusReq extends RequestSequence{
	/*消息ID*/
	private String id;
	
	/*状态:1/已读,0/未读*/
	private String isRead;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIsRead() {
		return isRead;
	}
	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	
}
