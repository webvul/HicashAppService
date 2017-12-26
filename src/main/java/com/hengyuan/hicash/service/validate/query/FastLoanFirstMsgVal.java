package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.FastLoanFirstReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author Administrator
 *
 */
public class FastLoanFirstMsgVal {

private FastLoanFirstReq queryMsgReq;
	
	public FastLoanFirstMsgVal(FastLoanFirstReq queryMsgReq){
		
		this.queryMsgReq = queryMsgReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(queryMsgReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(queryMsgReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		if (RegexValidate.isNull(queryMsgReq.getTempAppNo())) {
			return ResultCodes.STU_APP_QUERY_FASTLOAN_NO;
		}
		if (!RegexValidate.isUsername(queryMsgReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public FastLoanFirstReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(FastLoanFirstReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}

}
