package com.hengyuan.hicash.entity.param;

public class PicConfigEntity {

	/** 图片类型 */
	private String picType;

	/** 图片备注名 */
	private String picDescName;

	/** 图片最大上传数量 */
	private Integer picMaxNum;

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getPicDescName() {
		return picDescName;
	}

	public void setPicDescName(String picDescName) {
		this.picDescName = picDescName;
	}

	public Integer getPicMaxNum() {
		return picMaxNum;
	}

	public void setPicMaxNum(Integer picMaxNum) {
		this.picMaxNum = picMaxNum;
	}

}
