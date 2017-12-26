package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.AppAgainBasicReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class AppAgainBasicVal {
	
	private AppAgainBasicReq AppAgainBasicReq;

	public AppAgainBasicVal(AppAgainBasicReq appAgainBasicReq){
		this.AppAgainBasicReq = appAgainBasicReq;
	}
	
	public String validate(){
		if (RegexValidate.isNull(AppAgainBasicReq.getAppTempNo())) {
			return ResultCodes.SERVICEPSWVAL_TEMPAPPNO_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}

	public AppAgainBasicReq getAppAgainBasicReq() {
		return AppAgainBasicReq;
	}

	public void setAppAgainBasicReq(AppAgainBasicReq appAgainBasicReq) {
		AppAgainBasicReq = appAgainBasicReq;
	}
	
}
