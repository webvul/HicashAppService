package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 根据商户id查询售点
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SaleSiteBySupplierMsgResp extends ParmResponse {

	private List<SaleSiteEntity> saleSiteList;

	/**
	 * @return the saleSiteList
	 */
	public List<SaleSiteEntity> getSaleSiteList() {
		return saleSiteList;
	}

	/**
	 * @param saleSiteList
	 *            the saleSiteList to set
	 */
	public void setSaleSiteList(List<SaleSiteEntity> saleSiteList) {
		this.saleSiteList = saleSiteList;
	}

}
