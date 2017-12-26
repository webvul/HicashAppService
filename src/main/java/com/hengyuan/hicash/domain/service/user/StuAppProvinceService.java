package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.ProvinceEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppProvinceResp;

public class StuAppProvinceService implements RespService {

	private ProvinceQuery provinceQuery = new ProvinceQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		// StuAppProvinceReq provinceReq = (StuAppProvinceReq)parmRequest;

		StuAppProvinceResp provinceResp = new StuAppProvinceResp();
		/** 分期类型 */

		List<ProvinceEntity> provinceList = new ArrayList<ProvinceEntity>();

		// 特色分期,根据客户类型查询开放的省份
		provinceList = provinceQuery.queryAllProvince();

		provinceResp.setProvinces(provinceList);
		provinceResp.setResultCode(ResultCodes.NORMAL);
		return provinceResp;
	}
}
