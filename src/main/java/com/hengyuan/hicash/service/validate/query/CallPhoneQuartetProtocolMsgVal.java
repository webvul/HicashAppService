package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocolMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 	电信签订四方协议展示，姓名，申请产品名字，每月还款日，每月还款：元期:验证类
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPhoneQuartetProtocolMsgVal extends RegexValidate {
	
	private CallPhoneQuartetProtocolMsgReq queryMsgReq;
	
	public CallPhoneQuartetProtocolMsgVal(CallPhoneQuartetProtocolMsgReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(queryMsgReq.getAppNo())) {
			return ResultCodes.REGISTER_LIVECITY_ISNULL;
		}
		if(RegexValidate.isNull(queryMsgReq.getAppNo())){
			return ResultCodes.USER_UPDATE_APPNO_NOTNULL;
		}
		
		if(!RegexValidate.isNumber(queryMsgReq.getAppNo())||!RegexValidate.isLength(queryMsgReq.getAppNo(), 14,14)){
			return ResultCodes.USER_UPDATE_APPNO_FALSE;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public CallPhoneQuartetProtocolMsgReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(CallPhoneQuartetProtocolMsgReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}




}
