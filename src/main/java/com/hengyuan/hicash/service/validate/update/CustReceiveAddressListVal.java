package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CustReceiveAddressReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CustReceiveAddressListVal {
	
	private CustReceiveAddressReq req;

	public CustReceiveAddressListVal(CustReceiveAddressReq req) {
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
		
		if (RegexValidate.isNull(req.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(req.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		/* 用户名 */
		if (RegexValidate.isNull(req.getCust_user())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(req.getCust_user())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}


		
		return ResultCodes.NORMAL;

	}


}
