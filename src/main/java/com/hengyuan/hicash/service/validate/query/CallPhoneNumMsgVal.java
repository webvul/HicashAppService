package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CallPhoneNumMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 	根据城市ID,页数，显示条数获取电话号码:验证类
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneNumMsgVal  extends RegexValidate {
	
	private CallPhoneNumMsgReq queryMsgReq;
	
	public CallPhoneNumMsgVal(CallPhoneNumMsgReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(queryMsgReq.getCityCode())) {
			return ResultCodes.REGISTER_LIVECITY_ISNULL;
		}
		
		if (!RegexValidate.isSelectToAddress(queryMsgReq.getCityCode())) {
			return ResultCodes.REGISTER_LIVECITY_ILLEGAL;
		}

		if (RegexValidate.isNull(queryMsgReq.getPageIndex())) {
			return ResultCodes.CALL_PHONE_NUM_PAGEINDEX_FALSE;
		}
		if (!RegexValidate.isDigitPos(queryMsgReq.getPageIndex())) {
			return ResultCodes.CALL_PHONE_NUM_PAGEINDEX_FALSE;
		}
		if (RegexValidate.isNull(queryMsgReq.getPageNum())) {
			return ResultCodes.CALL_PHONE_NUM_PAGENUM_FALSE;
		}
		if (!RegexValidate.isDigitPos(queryMsgReq.getPageNum())) {
			return ResultCodes.CALL_PHONE_NUM_PAGENUM_FALSE;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public CallPhoneNumMsgReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(CallPhoneNumMsgReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}


}
