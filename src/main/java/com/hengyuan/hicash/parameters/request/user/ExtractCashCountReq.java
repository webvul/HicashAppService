package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 提现笔数 请求参数
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 * 
 */
public class ExtractCashCountReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 调整系数 */
	private String trimNum;

	public String getTrimNum() {
		return trimNum;
	}

	public void setTrimNum(String trimNum) {
		this.trimNum = trimNum;
	}

}
