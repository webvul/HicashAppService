package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 同款商品查询 请求参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-23
 *
 */
public class SameProductReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 城市代码 */
	private String cityCode;

	/** 排序标志 */
	private String orderMark;

	/** 页数(分页用) */
	private String pageNo = "1";

	/** 产品款型 */
	private String proClass;

	/** 每页显示最大行 */
	private Integer maxLine = 10;

	public Integer getMaxLine() {
		return maxLine;
	}

	public void setMaxLine(Integer maxLine) {
		this.maxLine = maxLine;
	}

	public String getProClass() {
		return proClass;
	}

	public void setProClass(String proClass) {
		this.proClass = proClass;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getOrderMark() {
		return orderMark;
	}

	public void setOrderMark(String orderMark) {
		this.orderMark = orderMark;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

}
