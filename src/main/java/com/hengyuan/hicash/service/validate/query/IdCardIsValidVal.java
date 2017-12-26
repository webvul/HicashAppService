package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.IdCardIsValidReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class IdCardIsValidVal {

	private IdCardIsValidReq idCardIsValidReq;

	public IdCardIsValidVal(IdCardIsValidReq idCardIsValidReq) {
		
		this.idCardIsValidReq=idCardIsValidReq;
	
	}
	public String validate() {

		if (RegexValidate.isNull(idCardIsValidReq.getUsername())) {
			return ResultCodes.USERNAME_NOT_NULL;
		}
		return ResultCodes.NORMAL;
	}
	
	public IdCardIsValidReq getIdCardIsValidReq() {
		return idCardIsValidReq;
	}
	public void setIdCardIsValidReq(IdCardIsValidReq idCardIsValidReq) {
		this.idCardIsValidReq = idCardIsValidReq;
	}
	
}

