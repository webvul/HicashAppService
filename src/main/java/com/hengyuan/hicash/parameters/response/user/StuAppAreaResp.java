package com.hengyuan.hicash.parameters.response.user;

import java.util.List;

import com.hengyuan.hicash.entity.param.AreaEntity;
import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请根据城市查询区返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppAreaResp extends ParmResponse{

//	/** 返回代码 */
//	private String resultCode;
//	
	/** 省份列表 */
	private List<AreaEntity> areas;

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
	 * @return the areas
	 */
	public List<AreaEntity> getAreas() {
		return areas;
	}

	/**
	 * @param areas the areas to set
	 */
	public void setAreas(List<AreaEntity> areas) {
		this.areas = areas;
	}


}
