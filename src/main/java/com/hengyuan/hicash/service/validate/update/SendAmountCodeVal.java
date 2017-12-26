package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SendAmountCodeReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class SendAmountCodeVal {

	private SendAmountCodeReq amountCodeReq;

	public SendAmountCodeVal(SendAmountCodeReq amountCodeReq) {
		this.amountCodeReq = amountCodeReq;
	}
	
	public String validate(){
		
		/* 验证uuid */
		if(RegexValidate.isNull(amountCodeReq.getUuid())){
			return ResultCodes.UUIDNULL;
		}
		
		if(!RegexValidate.isUuid(amountCodeReq.getUuid())){
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(amountCodeReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(amountCodeReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		if(RegexValidate.isNull(amountCodeReq.getMobile())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(amountCodeReq.getMobile())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}
		
		return ResultCodes.NORMAL;
	}

	public SendAmountCodeReq getAmountCodeReq() {
		return amountCodeReq;
	}

	public void setAmountCodeReq(SendAmountCodeReq amountCodeReq) {
		this.amountCodeReq = amountCodeReq;
	}

}
