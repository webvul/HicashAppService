package com.hengyuan.hicash.domain.service.user;

import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ReceiveAddressEvent;
import com.hengyuan.hicash.domain.query.param.AreaQuery;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.query.param.ProvinceQuery;
import com.hengyuan.hicash.domain.query.user.AddressQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustReceiveAddressEntity;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.parameters.response.user.NewCollarApp2MsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 查看列表
 * 
 * @author ding
 * 
 */
public class QueryCustAddressIdService implements RespService {

	private static Logger logger = Logger
			.getLogger(QueryCustAddressIdService.class);

	private String resultCode = "";

	private AddressQuery addressQuery = new AddressQuery();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CustReceiveAddressReq req = (CustReceiveAddressReq) parmRequest;
		CustAddressResp resp = new CustAddressResp();
		CustReceiveAddressEntity entity = null;

		try {
			
			entity = addressQuery.queryAddressById(req.getId());
			if(entity!=null){
				queryAddressName(entity);
			}
			resultCode = ResultCodes.NORMAL;
		
		
		} catch (Exception e) {
			resultCode = ResultCodes.QUERY_ADDRESS_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.QUERY_ADDRESS_EXCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}

		resp.setResultCode(resultCode);
		resp.setCustReceiveAddressEntity(entity);
		return resp;
	}
	
	
	
	/**
	 * 查询地址ID所对应的名称
	 * @param resp
	 * @return
	 */
	public void  queryAddressName(CustReceiveAddressEntity resp){
		ProvinceQuery provinceQuery = new ProvinceQuery();
		CityQuery cityQuery = new CityQuery();
		AreaQuery areaQuery = new AreaQuery();

		
		resp.setProvince_name(provinceQuery.queryProvince(resp.getProvince_code()).getProvName());
		resp.setCity_name(cityQuery.queryCity(resp.getCity_code()).getCityName());
		resp.setArea_name(areaQuery.queryArea(resp.getArea_code()).getAreaName());
		
		

		
		
	}


}
