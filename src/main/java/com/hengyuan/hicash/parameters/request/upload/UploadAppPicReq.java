package com.hengyuan.hicash.parameters.request.upload;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author fish
 *
 * @date 2017年1月10日 下午6:07:08
 */
public class UploadAppPicReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;

	private String imgType;

	private String uploadType;

	private String picName;

	private String bigPath;

	private String smallPath;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

	public String getUploadType() {
		return uploadType;
	}

	public void setUploadType(String uploadType) {
		this.uploadType = uploadType;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getBigPath() {
		return bigPath;
	}

	public void setBigPath(String bigPath) {
		this.bigPath = bigPath;
	}

	public String getSmallPath() {
		return smallPath;
	}

	public void setSmallPath(String smallPath) {
		this.smallPath = smallPath;
	}

}
