package com.hengyuan.hicash.domain.service.mktApp;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.param.CityQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.param.CityEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.QueryCityByNameReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.QueryCityByNameResp;

/**
 * 根据名称获取城市 service
 * @author Cary.Liu
 * @createDate 2015-05-19
 *
 */
public class QueryCityByNameService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		QueryCityByNameReq cityReq = (QueryCityByNameReq)parmRequest;
		QueryCityByNameResp cityResp = new QueryCityByNameResp();
		
		try {
			
			CityEntity entity = queryCity(cityReq);
			if(entity != null){
				cityResp.setCityCode(entity.getCityCode());
				cityResp.setCityName(entity.getCityName());
				resultCode = ResultCodes.NORMAL;
			}else{
				resultCode = ResultCodes.QUERYCITY_CITY_NOTFOUND;
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.QUERYCITY_EXCEPTION;
			e.printStackTrace();
		} finally {
			ConnManager.closeConn();
		}
		
		cityResp.setResultCode(resultCode);
		return cityResp;
	}
	
	protected CityEntity queryCity(QueryCityByNameReq cityReq){
		
		CityQuery query = new CityQuery();
		return query.queryCityByName(cityReq.getCityName());
	}

}
