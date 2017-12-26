package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CheckAgeReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CheckAgeVal extends RegexValidate {
	
	private CheckAgeReq checkAgeReq;
	
	public CheckAgeVal(CheckAgeReq checkAgeReq){
		
		this.checkAgeReq = checkAgeReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(checkAgeReq.getUsername())) {
			return ResultCodes.USERNAME_NOT_NULL;
		}
		return ResultCodes.NORMAL;
	}

	public CheckAgeReq getCheckAgeReq() {
		return checkAgeReq;
	}

	public void setCheckAgeReq(CheckAgeReq checkAgeReq) {
		this.checkAgeReq = checkAgeReq;
	}

	
	

}
