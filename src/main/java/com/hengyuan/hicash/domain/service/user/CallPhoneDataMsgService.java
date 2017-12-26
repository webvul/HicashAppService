package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.CallPhoneDataMsgQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneDataMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 	根据城市ID获取电信套餐内容逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneDataMsgService implements RespService {
	

	private static Logger logger = Logger.getLogger(CallPhoneDataMsgService.class);

	private CallPhoneDataMsgQuery callPhoneQuery = new CallPhoneDataMsgQuery();
	

	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		CallPhoneDataMsgReq req = (CallPhoneDataMsgReq) parmRequest;
		CallPhoneDataMsgResp resp = null;
		
		try{
				resp = callPhoneQuery.queryCallPhoneDataResp(req.getCityCode());
				if(resp != null){
					resp.setResultCode(ResultCodes.NORMAL);
					
				}else{
					resp = new CallPhoneDataMsgResp();
					resp.setResultCode(ResultCodes.CALL_PHONE_DATA_NOT_FOUND);
				}
			
			
		}catch (Exception e) {
			resp = new CallPhoneDataMsgResp();
			resp.setResultCode(ResultCodes.CALL_PHONE_DATA_QUERY_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.CALL_PHONE_DATA_QUERY_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
	
	

}
