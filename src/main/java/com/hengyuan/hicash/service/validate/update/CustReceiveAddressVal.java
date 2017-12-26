package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CustReceiveAddressVal {
	
	private CustReceiveAddressReq req;

	public CustReceiveAddressVal(CustReceiveAddressReq req) {
		super();
		this.req = req;
	}

	public CustReceiveAddressReq getReq() {
		return req;
	}

	public void setReq(CustReceiveAddressReq req) {
		this.req = req;
	}

	public String validate() {

		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(req.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(req.getId())) {
			return ResultCodes.ADDRESS_ID_ISNULL;
		}
		

		
		return ResultCodes.NORMAL;

	}


}
