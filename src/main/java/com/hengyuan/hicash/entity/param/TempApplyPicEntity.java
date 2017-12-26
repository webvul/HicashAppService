package com.hengyuan.hicash.entity.param;

/**
 * 临时申请件图片
 * 
 * @author Cary.Liu
 * 
 */
public class TempApplyPicEntity {

	private String picId;

	/** 用户名 */
	private String userName;

	/** 图片类型 */
	private String picType;

	private String picName;

	/** 大图url */
	private String picPath;

	/** 小图url */
	private String thumPath;

	private String picCaption;

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getThumPath() {
		return thumPath;
	}

	public void setThumPath(String thumPath) {
		this.thumPath = thumPath;
	}

	public String getPicCaption() {
		return picCaption;
	}

	public void setPicCaption(String picCaption) {
		this.picCaption = picCaption;
	}

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

}
