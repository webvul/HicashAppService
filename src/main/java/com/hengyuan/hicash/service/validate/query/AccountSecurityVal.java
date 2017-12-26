package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.AccountSecurityReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class AccountSecurityVal extends RegexValidate {

	private AccountSecurityReq req;
	
	public AccountSecurityVal(AccountSecurityReq req){
		
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

	public AccountSecurityReq getReq() {
		return req;
	}

	public void setReq(AccountSecurityReq req) {
		this.req = req;
	}
}
