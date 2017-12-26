package com.hengyuan.hicash.domain.service.user;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.query.user.RegisterCountQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.RegisterCountReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.RegisterCountResp;

/**
 * 注册人数统计 逻辑
 * 
 * @author Cary.Liu
 * @create 2014-09-17
 *
 */
public class RegisterCountService implements RespService {

	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		RegisterCountReq countReq = (RegisterCountReq)parmRequest;
		RegisterCountResp countResp = new RegisterCountResp();
		
		try {
			
			countResp.setRegisterCount(queryRegCount(countReq.getRegisterType()));
			resultCode = ResultCodes.NORMAL;
			
		} catch (Exception e) {
			resultCode = ResultCodes.REGISTERCOUNT_EXCEPTION;
		}
		
		countResp.setResultCode(resultCode);
		return countResp;
	}
	
	/**
	 * 查询注册人数
	 * @param regType 注册类型
	 * @return
	 */
	public String queryRegCount(String regType){
		
		RegisterCountQuery countQuery = new RegisterCountQuery();
		
		return countQuery.queryRegCount(regType) + "";
	}

}
