package com.hengyuan.hicash.domain.service.user;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.CustomerEvent;
import com.hengyuan.hicash.domain.query.user.CustcustomerQuery;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.entity.user.CustContactInfo;
import com.hengyuan.hicash.exception.UpdateCustomerException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.request.user.UpdateRelaNameReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.parameters.response.user.UpdateRelaNameResp;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.StringUtils;

/** 
 * @author dong.liu 
 * 2017-8-28 下午4:20:16
 * 类说明 
 */
public class UpdateRelaNameService implements RespService {
	
	private static Logger logger=Logger.getLogger(UpdateRelaNameService.class);
	
	private CustomerEvent customerEvent=new CustomerEvent();
	
	private String resultCode = "";
	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		UpdateRelaNameReq req = (UpdateRelaNameReq) parmRequest;
		UpdateRelaNameResp resp = new UpdateRelaNameResp();
		Map<String, Object> setMap=new HashMap<String, Object>();
		
		if(!StringUtils.isEmpty(req.getRealName())){
			setMap.put("NAME", req.getRealName());
		}
		setMap.put("NICK_NAME", "1");
		Map<String, Object> whereMap=new HashMap<String, Object>();
		whereMap.put("USERNAME", req.getUserName());
		
		try {
			customerEvent.updateCustomerInfo(setMap, whereMap);
			resultCode = ResultCodes.NORMAL;
			
		} catch (UpdateCustomerException e) {
			resultCode = ResultCodes.UPDATE_RELANAME_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.UPDATE_RELANAME_EXCEPTION, e);
		}
		
		resp.setResultCode(resultCode);
		return resp;
	}

}
