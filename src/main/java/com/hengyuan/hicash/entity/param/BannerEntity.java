package com.hengyuan.hicash.entity.param;

/**
 * 首页图片
 * 
 * @author Cary.Liu
 * @createDate 2015-06-05
 *
 */
public class BannerEntity {

	/** 图片标题 */
	private String picTitle;

	/** 图片跳转url */
	private String picActionUrl;

	/** 大图路径 */
	private String pigBigPath;

	/** 小图路径 */
	private String picSmallPath;

	public String getPicTitle() {
		return picTitle;
	}

	public void setPicTitle(String picTitle) {
		this.picTitle = picTitle;
	}

	public String getPicActionUrl() {
		return picActionUrl;
	}

	public void setPicActionUrl(String picActionUrl) {
		this.picActionUrl = picActionUrl;
	}

	public String getPigBigPath() {
		return pigBigPath;
	}

	public void setPigBigPath(String pigBigPath) {
		this.pigBigPath = pigBigPath;
	}

	public String getPicSmallPath() {
		return picSmallPath;
	}

	public void setPicSmallPath(String picSmallPath) {
		this.picSmallPath = picSmallPath;
	}

}
