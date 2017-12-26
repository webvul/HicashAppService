package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.dao.collection.ConnManager;
import com.hengyuan.hicash.domain.event.user.CallPHoneQuarteProtocalUpEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocalUpReq;
import com.hengyuan.hicash.parameters.response.user.CallPhoneQuartetProtocalUpResp;
import com.hengyuan.hicash.utils.RecordUtils;

/**
 * @author Administrator
 *
 */
public class CallPhoneQuartetProtocolUpService implements RespService {
	

	private static Logger logger = Logger.getLogger(CallPhoneQuarteProtocolMsgService.class);

	private CallPHoneQuarteProtocalUpEvent updadteEvent = new CallPHoneQuarteProtocalUpEvent();

	@Override
	public CallPhoneQuartetProtocalUpResp responseValue(ParmRequest parmRequest) {
		CallPhoneQuartetProtocalUpReq req = (CallPhoneQuartetProtocalUpReq) parmRequest;
		CallPhoneQuartetProtocalUpResp resp = new CallPhoneQuartetProtocalUpResp();

		String resultCode = "";
		String resultMsg = ""; // 返回消息
		try {

			ConnManager.beginTransaction();
			
			updadteEvent.updatEmail(req);
			ConnManager.commit();
			resultCode = ResultCodes.NORMAL;		
		} catch (Exception e) {
			ConnManager.rollback();
			resultCode = ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_EXCEPTION;
			/* 记录错误信息 */
			RecordUtils.writeError(logger, parmRequest.getUuid(), ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_EXCEPTION, e);
		} finally {
			ConnManager.closeConn();
		}

		resp.setResultCode(resultCode);
		resp.setResultMsg(resultMsg);
		return resp;
		}


}
