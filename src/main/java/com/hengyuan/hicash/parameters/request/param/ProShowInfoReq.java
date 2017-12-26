package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 产品展示信息 请求参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 城市代码 */
	private String cityCode;

	/** 频道ID */
	private String channelId;

	/** 价格筛选 */
	private String priceSearch;

	/** 商圈 */
	private String businessCircle;

	public String getBusinessCircle() {
		return businessCircle;
	}

	public void setBusinessCircle(String businessCircle) {
		this.businessCircle = businessCircle;
	}

	public String getPriceSearch() {
		return priceSearch;
	}

	public void setPriceSearch(String priceSearch) {
		this.priceSearch = priceSearch;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

}
