package com.hengyuan.hicash.parameters.response.upload;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 图片单张上传 返回参数
 * 
 * @author Cary.Liu
 * @create 2014-10-16
 *
 */
public class SaveSingleImgResp extends ParmResponse {

	private String picUrl;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
