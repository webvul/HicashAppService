package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.CallPhoneNumMsgQuery;
import com.hengyuan.hicash.domain.query.user.CallPhoneNumMsgQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CallPhoneNumMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CallPhoneNumMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 	根据城市ID,页数，显示条数获取电话号码逻辑类
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneNumMsgService implements RespService {
	

	private static Logger logger = Logger.getLogger(CallPhoneNumMsgService.class);

	private CallPhoneNumMsgQuery callPhoneQuery = new CallPhoneNumMsgQuery();
	

	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		CallPhoneNumMsgReq req = (CallPhoneNumMsgReq) parmRequest;
		CallPhoneNumMsgResp resp = null;
		
		try{
				resp = callPhoneQuery.queryPhoneList(req);
				if(resp != null){
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					
					resp = new CallPhoneNumMsgResp();
					resp.setResultCode(ResultCodes.CALL_PHONE_NUM_NOT_FOUND);
				}
			
			
		}catch (Exception e) {
			resp = new CallPhoneNumMsgResp();
			resp.setResultCode(ResultCodes.CALL_PHONE_NUM_QUERY_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.CALL_PHONE_NUM_QUERY_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
	
	

}
