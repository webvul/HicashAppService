package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.RegisterQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.UserExistReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.UserExistResp;
import com.hengyuan.hicash.utils.RecordUtils;

public class UserExistService implements RespService {
	
	private static Logger logger = Logger.getLogger(UserExistService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		UserExistReq existReq = (UserExistReq)parmRequest;
		RegisterQuery query = new RegisterQuery();
		UserExistResp existResp = new UserExistResp();
		
		try {
			
			boolean userExist = query.isUserExist(existReq.getUserName());
			
			if(userExist){
				//用户名已存在
//				existResp.setResultCode(ResultCodes.REGISTER_EXIST);
			}else{
				//用户名未存在
				existResp.setResultCode(ResultCodes.NORMAL);
			}
			
		} catch (Exception e) {
//			existResp.setResultCode(ResultCodes.USER_QUERY_EXCEPTION);
		} finally {
			ConnManager.closeConn();
		}

		
		return existResp;
	}

	
	
	
	
}
