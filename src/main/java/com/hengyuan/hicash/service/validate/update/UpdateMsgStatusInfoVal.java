package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UpdateMsgStatusReq;
import com.hengyuan.hicash.utils.RegexValidate;

/** 
 * 
 * 类说明 :参数验证
 */
public class UpdateMsgStatusInfoVal {
	
	private UpdateMsgStatusReq req;
	
	public UpdateMsgStatusInfoVal(UpdateMsgStatusReq req){
		this.req = req;
	}
	public String validate(){
		
		/**
		 *  消息id
		 */
		if(RegexValidate.isNull(req.getId())){
			return ResultCodes.NOTICE_DETAIL_ID_NOTNULL;
		}
		/**
		 * 状态:1/已读,0/未读
		 */
		if(RegexValidate.isNull(req.getIsRead())){
			return ResultCodes.STATUS_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}
}
