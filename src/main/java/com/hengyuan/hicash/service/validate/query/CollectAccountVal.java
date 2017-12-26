package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CollectAccountReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CollectAccountVal {
	
	private CollectAccountReq accountReq;
	
	public CollectAccountReq getAccountReq() {
		return accountReq;
	}

	public void setAccountReq(CollectAccountReq accountReq) {
		this.accountReq = accountReq;
	}

	public CollectAccountVal(CollectAccountReq accountReq){
		
		this.accountReq = accountReq;
		
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(accountReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(accountReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(accountReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(accountReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		
		return ResultCodes.NORMAL;
		
	}

}
