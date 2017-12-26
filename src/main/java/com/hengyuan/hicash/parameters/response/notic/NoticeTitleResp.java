package com.hengyuan.hicash.parameters.response.notic;

import java.util.List;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 获取公告列表标题
 * 
 * @author Cary.Liu
 * @create 2014-08-15
 * 
 */
public class NoticeTitleResp extends ParmResponse {

	/** 返回代码 */
	private String resultCode;
	
	/** 返回数据集合 */
	private List<NoticDetilResp> noticeTtiles;

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public List<NoticDetilResp> getNoticeTtiles() {
		return noticeTtiles;
	}

	public void setNoticeTtiles(List<NoticDetilResp> noticeTtiles) {
		this.noticeTtiles = noticeTtiles;
	}
	

	


}
