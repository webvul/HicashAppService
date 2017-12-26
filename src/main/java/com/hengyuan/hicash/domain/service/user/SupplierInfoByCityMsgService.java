package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.mer.SupplierQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.mer.SupplierEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SupplierInfoByCityMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SupplierInfoByCityMsgResp;
/**
 * 根据城市code查询商户
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SupplierInfoByCityMsgService implements RespService {

	private SupplierQuery cityQuery = new SupplierQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SupplierInfoByCityMsgReq cityReq = (SupplierInfoByCityMsgReq) parmRequest;

		SupplierInfoByCityMsgResp cityResp = new SupplierInfoByCityMsgResp();

		List<SupplierEntity> supplierList = new ArrayList<SupplierEntity>();

		// 根据城市code查询商户
		supplierList = cityQuery.queryMerByCity(cityReq.getCityCode());

		cityResp.setSupplierList(supplierList);
		cityResp.setResultCode(ResultCodes.NORMAL);

		return cityResp;
	}
}
