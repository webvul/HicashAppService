package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.DkInfoByAppNoMsgReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class DkInfoByAppNoMsgVal extends RegexValidate {
	
	private DkInfoByAppNoMsgReq queryMsgReq;
	
	public DkInfoByAppNoMsgVal(DkInfoByAppNoMsgReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
//		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
//			return ResultCodes.UUIDNULL;
//		}
//		
//		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
//			return ResultCodes.UUIDILLEGAL;
//		}
//		
		if (RegexValidate.isNull(queryMsgReq.getUserName())) {
			return ResultCodes.ADDMERAPP_USERNAME_ISNULL;
		}
		
		
		
		return ResultCodes.NORMAL;
	}

	public DkInfoByAppNoMsgReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(DkInfoByAppNoMsgReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}
	
}
