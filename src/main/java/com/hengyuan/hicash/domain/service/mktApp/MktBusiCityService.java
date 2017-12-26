package com.hengyuan.hicash.domain.service.mktApp;

import java.util.List;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.mktApp.MktBusiCityQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.mktApp.MktBusiCityEntity;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.mktApp.MktBusiCityReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.mktApp.MktBusiCityResp;

/**
 * 二次营销-业务开放城市 业务处理
 * @author Cary.Liu
 * @create 2015-03-13
 *
 */
public class MktBusiCityService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		MktBusiCityReq mktReq = (MktBusiCityReq)parmRequest;
		MktBusiCityResp mktResp = new MktBusiCityResp();
		
		try {
			
			 List<MktBusiCityEntity> list = queryBusiCityList(mktReq.getCustType());
			 if(list != null && list.size() > 0){
				 
				 mktResp.setList(list);
				 resultCode = ResultCodes.NORMAL;
			 }else{
				 resultCode = ResultCodes.MKTAPP_BUSICITY_NORESULT;
			 }
			
		} catch (Exception e) {
			resultCode = ResultCodes.MKTAPP_BUSICITY_EXCEPTION;
		} finally {
			ConnManager.closeConn();
		}
		
		mktResp.setResultCode(resultCode);
		return mktResp;
	}
	
	/**
	 * 二次营销-获取业务开放城市
	 * @param custType
	 * @return
	 */
	public List<MktBusiCityEntity> queryBusiCityList(String custType){
		
		MktBusiCityQuery mktQuery = new MktBusiCityQuery();
		
		return mktQuery.queryBusiCityList(custType);
	}

}
