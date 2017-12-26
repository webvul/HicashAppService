package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.param.CityEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请根据省查询城市返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppCityResp extends ParmResponse{

//	/** 返回代码 */
//	private String resultCode;
	
	/** 省份列表 */
	private List<CityEntity> citys;

//	/**
//	 * @return the resultCode
//	 */
//	public String getResultCode() {
//		return resultCode;
//	}
//
//	/**
//	 * @param resultCode the resultCode to set
//	 */
//	public void setResultCode(String resultCode) {
//		this.resultCode = resultCode;
//	}

	/**
	 * @return the citys
	 */
	public List<CityEntity> getCitys() {
		return citys;
	}

	/**
	 * @param citys the citys to set
	 */
	public void setCitys(List<CityEntity> citys) {
		this.citys = citys;
	}

	

	
	

}
