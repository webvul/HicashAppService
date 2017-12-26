package com.hengyuan.hicash.parameters.response.mktApp;

import java.util.List;

import com.hengyuan.hicash.entity.mktApp.MktBusiCityEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 二次营销-业务开放城市 返回参数
 * 
 * @author Cary.Liu
 * @create 2015-03-13
 *
 */
public class MktBusiCityResp extends ParmResponse {

	/** 城市集合 */
	private List<MktBusiCityEntity> list;

	public List<MktBusiCityEntity> getList() {
		return list;
	}

	public void setList(List<MktBusiCityEntity> list) {
		this.list = list;
	}

}
