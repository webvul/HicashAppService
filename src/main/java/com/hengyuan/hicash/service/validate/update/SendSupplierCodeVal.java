package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.notic.SendSupplierCodeReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class SendSupplierCodeVal {

	private SendSupplierCodeReq codeReq;

	public SendSupplierCodeVal(SendSupplierCodeReq codeReq) {
		this.codeReq = codeReq;
	}

	public String validate(){
		
		if(RegexValidate.isNull(codeReq.getMobileNo())){
			return ResultCodes.SENDSUPPLIERCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(codeReq.getMobileNo())){
			return ResultCodes.SENDSUPPLIERCODE_MOBILE_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	
	public SendSupplierCodeReq getCodeReq() {
		return codeReq;
	}

	public void setCodeReq(SendSupplierCodeReq codeReq) {
		this.codeReq = codeReq;
	}

}
