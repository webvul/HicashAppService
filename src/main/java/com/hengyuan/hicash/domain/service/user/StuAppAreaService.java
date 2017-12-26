package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.AreaEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.StuAppAreaReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.StuAppAreaResp;

/**
 * @author Administrator
 *
 */
public class StuAppAreaService implements RespService {

	private AreaQuery areaQuery = new AreaQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		StuAppAreaReq areaReq = (StuAppAreaReq) parmRequest;

		StuAppAreaResp areaResp = new StuAppAreaResp();

		List<AreaEntity> areaList = new ArrayList<AreaEntity>();

		// 特色分期,根据客户类型查询开放的省份
		areaList = areaQuery.queryAreaByCity(areaReq.getCityCode());

		areaResp.setAreas(areaList);
		areaResp.setResultCode(ResultCodes.NORMAL);

		return areaResp;
	}

}
