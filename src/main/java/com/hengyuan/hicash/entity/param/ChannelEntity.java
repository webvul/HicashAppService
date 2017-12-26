package com.hengyuan.hicash.entity.param;

/**
 * 频道表
 * 
 * @author Cary.Liu
 * 
 *
 */
public class ChannelEntity {

	/** 主键 */
	private Integer channelId;

	/** 频道名称 */
	private String channelName;

	/** 顺序 */
	private Integer showSequence;

	/** 创建时间 */
	private String createDate;

	/** 城市代码 */
	private String cityCode;

	/** 行业代码 */
	private String industryCode;

	/** 频道所展示的图片 */
	private String showPicPath;

	public String getShowPicPath() {
		return showPicPath;
	}

	public void setShowPicPath(String showPicPath) {
		this.showPicPath = showPicPath;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public Integer getShowSequence() {
		return showSequence;
	}

	public void setShowSequence(Integer showSequence) {
		this.showSequence = showSequence;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

}
