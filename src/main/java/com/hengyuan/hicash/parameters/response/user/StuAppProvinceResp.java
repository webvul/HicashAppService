package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.param.ProvinceEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;


/**
 * hicash手机端学生提现申请查询省返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppProvinceResp extends ParmResponse{

	/** 省份列表 */
	private List<ProvinceEntity> provinces;

	/**
	 * @return the provinces
	 */
	public List<ProvinceEntity> getProvinces() {
		return provinces;
	}

	/**
	 * @param provinces the provinces to set
	 */
	public void setProvinces(List<ProvinceEntity> provinces) {
		this.provinces = provinces;
	}
	
	

}
