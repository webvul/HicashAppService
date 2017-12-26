package com.hengyuan.hicash.domain.service.auth;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.FastLoanFirstUpEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.exception.UpdateInfoException;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.auth.DriverAuthSaveReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.auth.DriverAuthSaveResp;

public class DriverAuthSaveService implements RespService {

	private static Logger logger = Logger.getLogger(DriverAuthSaveService.class);
	private FastLoanFirstUpEvent updateMsgEvent = new FastLoanFirstUpEvent();

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		DriverAuthSaveReq req = (DriverAuthSaveReq) parmRequest;

		DriverAuthSaveResp resp = new DriverAuthSaveResp();
		String resultCode = "";
		try {
			ConnManager.beginTransaction();
			updateMsgEvent.updateDriverUsername(req.getDriverUsername(), req.getTempApplicationNo(), req.getUuid());
			ConnManager.commit();
			resultCode = ResultCodes.NORMAL;

		} catch (UpdateInfoException e) {
			e.printStackTrace();
			logger.error(e);
			resultCode = ResultCodes.FAST_LOAN_SAVE_EXCEPTION;
		}
		resp.setResultCode(resultCode);

		return resp;
	}

}
