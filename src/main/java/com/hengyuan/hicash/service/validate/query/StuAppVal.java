package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.StuAppReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * hicash手机端学生提现申请查询验证
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuAppVal extends RegexValidate {
	
	private StuAppReq queryMsgReq;
	
	public StuAppVal(StuAppReq queryMsgReq){
		
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
		
		return ResultCodes.NORMAL;
	}
	
	
	public StuAppReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(StuAppReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}
}
