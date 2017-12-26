package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 	根据城市ID获取电信套餐内容:验证类
 * 
 * @author lihua.Ren
 * @create date 2015-08-18
 *
 */
public class CallPhoneDataMsgVal  extends RegexValidate {
	
	private CallPhoneDataMsgReq queryMsgReq;
	
	public CallPhoneDataMsgVal(CallPhoneDataMsgReq queryMsgReq){
		
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

		return ResultCodes.NORMAL;
	}
	
	
	public CallPhoneDataMsgReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(CallPhoneDataMsgReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}


}
