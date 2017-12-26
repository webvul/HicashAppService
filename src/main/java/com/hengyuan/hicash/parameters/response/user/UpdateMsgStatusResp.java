package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class UpdateMsgStatusResp extends ParmResponse{
	
	/*状态:1/已读,0/未读*/
	private String isRead;

	public String getIsRead() {
		return isRead;
	}

	public void setIsRead(String isRead) {
		this.isRead = isRead;
	}
	

}
