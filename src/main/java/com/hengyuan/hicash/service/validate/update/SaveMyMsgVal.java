package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SaveMyMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 
* @author 
* 类说明:我的消息validate验证
 */
public class SaveMyMsgVal {
	
	 private SaveMyMsgReq valReq;

	public SaveMyMsgVal(SaveMyMsgReq valReq) {
		this.valReq = valReq;
	}
	public String validate(){
		
		
		/* 状态*/
		if(RegexValidate.isNull(valReq.getStatus())){
			return ResultCodes.STATUS_ISNULL;
		}
		/* 消息code*/
		if(RegexValidate.isNull(valReq.getCode())){
			return ResultCodes.DDSJ_CODE_IS_NULL;
		}
		/* 用户名 */
		if(RegexValidate.isNull(valReq.getUsername())){
			return ResultCodes.ADDMERAPP_USERNAME_ISNULL;
		}
		
		return ResultCodes.NORMAL;
		
	}
}
