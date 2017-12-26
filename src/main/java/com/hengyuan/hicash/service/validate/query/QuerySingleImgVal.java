package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.QuerySingleImgReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author Administrator
 *
 */
public class QuerySingleImgVal {
private QuerySingleImgReq queryMsgReq;
	
	public QuerySingleImgVal(QuerySingleImgReq queryMsgReq){
		
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
		
		if (!RegexValidate.isUsername(queryMsgReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
//		if (!RegexValidate.isAppNo(queryMsgReq.getTempAppNo())) {
//			return ResultCodes.STU_UPDATE_APPNO_ISNOTEXIST;
//		}
		return ResultCodes.NORMAL;
	}
	
	
	public QuerySingleImgReq getQueryMsgReq() {
		return queryMsgReq;
	}

}
