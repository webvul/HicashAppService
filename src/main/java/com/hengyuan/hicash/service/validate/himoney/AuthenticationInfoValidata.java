package com.hengyuan.hicash.service.validate.himoney;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.himoney.AuthenticationInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author xuexin
 * @date 2017年7月14日 14:26:24
 * 
 * 
 * **/
public class AuthenticationInfoValidata extends RegexValidate {
	
	private AuthenticationInfoReq req;
	
	public AuthenticationInfoValidata(AuthenticationInfoReq req){
		this.req = req;
	}

	public String validate(){
		
		
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(req.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}

	public String validateReq(){
		
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(req.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		if (RegexValidate.isNull(req.getTempAppNo())) {
			return ResultCodes.TEMPAPPNO_ERROR_ISNULL;
		}
		
		if (RegexValidate.isNull(req.getAuthId())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public AuthenticationInfoReq getReq() {
		return req;
	}

	public void setReq(AuthenticationInfoReq req) {
		this.req = req;
	}
	
	

}
