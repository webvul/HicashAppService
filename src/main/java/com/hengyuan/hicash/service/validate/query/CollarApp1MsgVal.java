package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CollarApp1MsgReq;
import com.hengyuan.hicash.utils.RegexValidate;



/**
 * 手机端hicash白领资料查询1参数验证
 * 
 * @author LiHua.Ren
 * @create date 2015-06-17
 *
 */
public class CollarApp1MsgVal  extends RegexValidate {
	
	private CollarApp1MsgReq queryMsgReq;
	
	public CollarApp1MsgVal(CollarApp1MsgReq queryMsgReq){
		
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
	
	
	public CollarApp1MsgReq getQueryMsgReq() {
		return queryMsgReq;
	}

	public void setQueryMsgReq(CollarApp1MsgReq queryMsgReq) {
		this.queryMsgReq = queryMsgReq;
	}
}


