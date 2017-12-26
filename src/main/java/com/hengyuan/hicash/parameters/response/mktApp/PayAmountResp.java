package com.hengyuan.hicash.parameters.response.mktApp;

import java.util.List;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 临时申请列表 返回参数
 * 
 * @author Cary.Liu
 * @create 2014-10-13
 *
 */
public class PayAmountResp extends ParmResponse {

	private String resultCode;

	/** 首付比率 */
	private List<String> patRate;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public List<String> getPatRate() {
		return patRate;
	}

	public void setPatRate(List<String> patRate) {
		this.patRate = patRate;
	}

}
