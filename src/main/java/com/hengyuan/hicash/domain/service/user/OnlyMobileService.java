package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.OnlyMobileQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.OnlyMobileReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.OnlyMobileResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 手机唯一性验证  业务处理
 * 
 * @author Cary.Liu
 * @create 2014-08-11
 *
 */
public class OnlyMobileService implements RespService {
	
	private static Logger logger = Logger.getLogger(OnlyMobileService.class);

	private OnlyMobileQuery mobileQuery = new OnlyMobileQuery();
	private String resultCode = "";
	private String resultMsg = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		OnlyMobileReq mobileReq = (OnlyMobileReq)parmRequest;
		OnlyMobileResp mobileResp = new OnlyMobileResp();;
		try {
			boolean isExistMobile = mobileQuery.isExistMobile(mobileReq.getMobile());
			if(isExistMobile){
				resultCode = ResultCodes.MOBILE_ONLY_VALIDATE_EXIST_TRUE;
				resultMsg = "";
			}else{
				resultCode = ResultCodes.MOBILE_ONLY_VALIDATE_EXIST_FALSE;
				resultMsg = "";
			}
			
		} catch (Exception e) {
			resultCode = ResultCodes.MOBILE_ONLY_VALIDATE_EXCEPTION;
			resultMsg = "";
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.MOBILE_ONLY_VALIDATE_EXCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}

		
		mobileResp.setResultCode(resultCode);
		mobileResp.setResultMsg(resultMsg);
		return mobileResp;
	}
	
	

}
