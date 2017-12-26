package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 获取频道列表 请求参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ChannelInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 频道类型 */
	private String channelType;

	/** 城市代码 */
	private String cityCode;

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getChannelType() {
		return channelType;
	}

	public void setChannelType(String channelType) {
		this.channelType = channelType;
	}

}
