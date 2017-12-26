package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.CityEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuAppCityReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppCityResp;

public class StuAppCityService implements RespService {

	private CityQuery cityQuery = new CityQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		StuAppCityReq cityReq = (StuAppCityReq) parmRequest;

		StuAppCityResp cityResp = new StuAppCityResp();

		List<CityEntity> cityList = new ArrayList<CityEntity>();

		// 特色分期,根据客户类型查询开放的省份
		cityList = cityQuery.queryCityByProCode(cityReq.getProvinceCode());

		cityResp.setCitys(cityList);
		cityResp.setResultCode(ResultCodes.NORMAL);

		return cityResp;
	}
}
