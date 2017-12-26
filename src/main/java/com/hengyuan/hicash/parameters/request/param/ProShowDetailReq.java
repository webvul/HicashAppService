package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 产品展示详情
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowDetailReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 商户产品ID */
	private String merProId;

	/** 获取售点标志 */
	private String querySiteMark;

	public String getQuerySiteMark() {
		return querySiteMark;
	}

	public void setQuerySiteMark(String querySiteMark) {
		this.querySiteMark = querySiteMark;
	}

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

}
