package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SendPassWordCodeReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class SendPassWordCodeVal extends RegexValidate {

	private SendPassWordCodeReq freq;

	public SendPassWordCodeVal(SendPassWordCodeReq freq) {

		this.freq = freq;

	}
	
	public String validate() {
		
		if (RegexValidate.isNull(freq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		if (!RegexValidate.isUuid(freq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(freq.getMobile())) {
			return ResultCodes.FORGETPASSWORd_PHONE_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isIphon(freq.getMobile())) {
			return ResultCodes.FORGETPASSWORd_PHONE_ERROR;
		}
		
		
		
		return ResultCodes.NORMAL;
	}

	public SendPassWordCodeReq getFreq() {
		return freq;
	}

	public void setFreq(SendPassWordCodeReq freq) {
		this.freq = freq;
	}
}
