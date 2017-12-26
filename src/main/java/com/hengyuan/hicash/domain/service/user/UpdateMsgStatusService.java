package com.hengyuan.hicash.domain.service.user;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.domain.event.user.UpdateMsgStatusEvent;
import com.hengyuan.hicash.domain.service.RespService;
import com.hengyuan.hicash.parameters.request.ParmRequest;
import com.hengyuan.hicash.parameters.request.user.UpdateMsgStatusReq;
import com.hengyuan.hicash.parameters.response.ParmResponse;
import com.hengyuan.hicash.parameters.response.user.UpdateMsgStatusResp;
import com.hengyuan.hicash.utils.ResourceUtils;

public class UpdateMsgStatusService implements RespService{

	private static Logger logger = Logger
			.getLogger(UpdateMsgStatusService.class);

	@Override
	public ParmResponse responseValue(ParmRequest parmRequest) {
		
		UpdateMsgStatusReq req = (UpdateMsgStatusReq)parmRequest;
		UpdateMsgStatusResp resp= new UpdateMsgStatusResp();
		
		String resultCode= "";
		try{
			UpdateMsgStatusEvent Event =new UpdateMsgStatusEvent();
			Event.UpdateMsgStatus(req);
			logger.error("修改状态成功");
			resultCode = ResultCodes.NORMAL;
		}catch(Exception e){
			logger.error("修改状态失败", e);
			resultCode = ResultCodes.REGISTER_APPLYTEMP_EXCEPTION;
		}
		
		resp.setResultCode(resultCode);
		resp.setResultMsg(ResourceUtils.getString(resp
				.getResultCode()));
		return resp;
	}

}
