package com.hengyuan.hicash.parameters.request.upload;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 单张上传 请求参数
 * 
 * @author Cary.Liu
 * @create 2015-05-26
 *
 */
public class SaveSingleImgReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 图片类型 */
	private String imgType;

	/** 用户名 */
	private String userName;

	/** 临时申请单号 */
	private String tempAppNo;

	/** 点击时间 */
	private String clickTime;

	public String getTempAppNo() {
		return tempAppNo;
	}

	public void setTempAppNo(String tempAppNo) {
		this.tempAppNo = tempAppNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getClickTime() {
		return clickTime;
	}

	public void setClickTime(String clickTime) {
		this.clickTime = clickTime;
	}

	public String getImgType() {
		return imgType;
	}

	public void setImgType(String imgType) {
		this.imgType = imgType;
	}

}
