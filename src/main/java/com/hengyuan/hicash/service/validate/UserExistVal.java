package com.hengyuan.hicash.service.validate;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.UserExistReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class UserExistVal extends RegexValidate{
	
	private UserExistReq userExistReq;
	
	public UserExistVal(UserExistReq userExistReq){
		this.userExistReq = userExistReq;
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(userExistReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(userExistReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(userExistReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(userExistReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public UserExistReq getUserExistReq() {
		return userExistReq;
	}

	public void setUserExistReq(UserExistReq userExistReq) {
		this.userExistReq = userExistReq;
	}

}

	
