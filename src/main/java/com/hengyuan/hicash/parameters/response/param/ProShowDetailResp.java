package com.hengyuan.hicash.parameters.response.param;

import java.util.List;

import com.hengyuan.hicash.entity.param.ProShowEntity;
import com.hengyuan.hicash.entity.param.SaleSiteListEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 产品展示详情 返回参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowDetailResp extends ParmResponse {

	/** 商品 */
	private ProShowEntity proShowEntity;

	private List<SaleSiteListEntity> siteList;

	public List<SaleSiteListEntity> getSiteList() {
		return siteList;
	}

	public void setSiteList(List<SaleSiteListEntity> siteList) {
		this.siteList = siteList;
	}

	public ProShowEntity getProShowEntity() {
		return proShowEntity;
	}

	public void setProShowEntity(ProShowEntity proShowEntity) {
		this.proShowEntity = proShowEntity;
	}

}
