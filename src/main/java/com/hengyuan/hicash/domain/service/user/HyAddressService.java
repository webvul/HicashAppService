package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.query.user.HyAddressAdd;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.HyAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.HyAddressResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 增加收货地址
 * @author tan
 *
 */
public class HyAddressService implements RespService {
	
	private static Logger logger = Logger.getLogger(HyAddressService.class);

	private HyAddressAdd hyAddressAdd = new HyAddressAdd();
	

	
	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		HyAddressReq req = (HyAddressReq) parmRequest;
		HyAddressResp resp = new HyAddressResp();
		
		try{
			    hyAddressAdd.addAddress(req);
				resp.setResultCode(ResultCodes.NORMAL);
		}catch (Exception e) {
			resp = new HyAddressResp();
			resp.setResultCode(ResultCodes.ADD_ADRESS_EXCEPTION);
			
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.SAVEAPPACCT_EXCEPTION1, e);
		} finally {
			ConnManager.closeConn();
		}

		
		return resp;
	}
	

}
