package com.hengyuan.hicash.parameters.request.upload;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 删除临时申请件图片 req
 * 
 * @author Cary.Liu
 * @createDate 2015-12-02
 *
 */
public class RemoveTempAppPicReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 图片ID */
	private String picId;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

}
