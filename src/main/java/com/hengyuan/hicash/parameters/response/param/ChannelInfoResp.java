package com.hengyuan.hicash.parameters.response.param;

import java.util.List;

import com.hengyuan.hicash.entity.param.ChannelEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 获取频道列表 返回参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ChannelInfoResp extends ParmResponse {

	/** 频道列表 */
	private List<ChannelEntity> list;

	public List<ChannelEntity> getList() {
		return list;
	}

	public void setList(List<ChannelEntity> list) {
		this.list = list;
	}

}
