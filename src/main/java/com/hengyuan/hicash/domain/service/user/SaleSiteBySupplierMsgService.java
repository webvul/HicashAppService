package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.mer.SaleSiteQuery;
import com.hengyuan.hicash.domain.query.mer.SupplierQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.mer.SaleSiteEntity;
import com.hengyuan.hicash.entity.mer.SupplierEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.SaleSiteBySupplierMsgReq;
import com.hengyuan.hicash.parameters.request.user.SupplierInfoByCityMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.SaleSiteBySupplierMsgResp;
import com.hengyuan.hicash.parameters.response.user.SupplierInfoByCityMsgResp;
/**
 * 根据商户id查询售点
 * 
 * @author lihua.Ren
 * @create date 2016-01-26
 *
 */
public class SaleSiteBySupplierMsgService implements RespService {

	private SaleSiteQuery saleQuery = new SaleSiteQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		SaleSiteBySupplierMsgReq supplierReq = (SaleSiteBySupplierMsgReq) parmRequest;

		SaleSiteBySupplierMsgResp supplierResp = new SaleSiteBySupplierMsgResp();

		List<SaleSiteEntity> saleSiteList = new ArrayList<SaleSiteEntity>();

		// 根据商户id查询售点
		saleSiteList = saleQuery.querySaleSiteBySup(supplierReq.getSupplierId());

		supplierResp.setSaleSiteList(saleSiteList);
		supplierResp.setResultCode(ResultCodes.NORMAL);

		return supplierResp;
	}
}
