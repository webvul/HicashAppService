package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.AccountStatusReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class AccountStatusVal {

	private AccountStatusReq req;

	public AccountStatusVal(AccountStatusReq req){
		
		this.req = req;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(req.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(req.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public AccountStatusReq getReq() {
		return req;
	}

	public void setReq(AccountStatusReq req) {
		this.req = req;
	}

}
