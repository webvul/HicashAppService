package com.hengyuan.hicash.domain.service.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.app.InputAppPayQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.app.InputAppPay;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.DkInfoByAppNoMsgReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.DkInfoByAppNoMsgResp;
import com.hengyuan.hicash.utils.RecordUtils;

public class DkInfoByAppNoMsgService implements RespService {
	

	private static Logger logger = Logger.getLogger(DkInfoByAppNoMsgService.class);

	private InputAppPayQuery inputAppPayQuery = new InputAppPayQuery();
	
	
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		DkInfoByAppNoMsgReq req = (DkInfoByAppNoMsgReq) parmRequest;
		DkInfoByAppNoMsgResp resp = new DkInfoByAppNoMsgResp();
		
		try{
			
			List<InputAppPay> inputAppList = inputAppPayQuery.queryDkInfoByAppNo(req.getUserName());
	
				if(inputAppList.size()>0){
					resp.setDkAppList(inputAppList);
					resp.setResultCode(ResultCodes.NORMAL);
				}else{
					
					resp.setDkAppList(new ArrayList<InputAppPay>());
					resp.setResultCode(ResultCodes.DK_INFO_BYAPP_NOTFOUND);
				}
			
			
		}catch (Exception e) {
			
			resp.setResultCode(ResultCodes.DK_INFO_BYAPP_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.DK_INFO_BYAPP_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
}
