package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.ReceiveAddressEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateAddressException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.CustAddressResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * 删除地址
 * 
 * @author ding
 * 
 */
public class UpdateAddressStatusService implements RespService {

	private static Logger logger = Logger
			.getLogger(UpdateAddressStatusService.class);

	private String resultCode = "";

	private ReceiveAddressEvent receiveAddressEvent = new ReceiveAddressEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {

		CustReceiveAddressReq req = (CustReceiveAddressReq) parmRequest;
		CustAddressResp resp = new CustAddressResp();

		try {
			
			receiveAddressEvent.UpdateStatus(req);
			
			resultCode = ResultCodes.NORMAL;
		
		
		} catch (UpdateAddressException e) {
			resultCode = ResultCodes.UPDATE_ADDRESS_EXCCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, req.getUuid(), ResultCodes.UPDATE_ADDRESS_EXCCEPTION, e);
		}finally {
			ConnManager.closeConn();
		}

		resp.setResultCode(resultCode);

		return resp;
	}

}
