package com.hengyuan.hicash.entity.param;

/**
 * 商户商品图片
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class MerProPicEntity {

	/** 主键 */
	private String picId;

	/** 产品ID */
	private String proId;

	/** 图片类型 */
	private String picType;

	/** 图片路径 */
	private String picPath;

	public String getPicId() {
		return picId;
	}

	public void setPicId(String picId) {
		this.picId = picId;
	}

	public String getProId() {
		return proId;
	}

	public void setProId(String proId) {
		this.proId = proId;
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

}
