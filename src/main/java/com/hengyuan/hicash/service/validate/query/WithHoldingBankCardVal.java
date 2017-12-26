package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.WithHoldingBankCardReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class WithHoldingBankCardVal extends RegexValidate {

	private WithHoldingBankCardReq req;
	
	public WithHoldingBankCardVal(WithHoldingBankCardReq req){
		
		this.req = req;
		
	}

	public String validate(){
		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(req.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(req.getAppNo())) {
			return ResultCodes.SERVICEPSWVAL_APPNO_ISNULL;
		}
		
		
		
		return ResultCodes.NORMAL;
	}

	public WithHoldingBankCardReq getReq() {
		return req;
	}

	public void setReq(WithHoldingBankCardReq req) {
		this.req = req;
	}
}
