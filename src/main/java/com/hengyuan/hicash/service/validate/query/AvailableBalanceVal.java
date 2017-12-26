package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.AvailBalanceReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class AvailableBalanceVal {


	private AvailBalanceReq availBalanceReq;

	public AvailableBalanceVal(AvailBalanceReq availBalanceReq) {
		this.availBalanceReq = availBalanceReq;
	}

	public String validate() {

		if (RegexValidate.isNull(availBalanceReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(availBalanceReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(availBalanceReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(availBalanceReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}

		return ResultCodes.NORMAL;
	}

	public AvailBalanceReq getAvailBalanceReq() {
		return availBalanceReq;
	}

	public void setAvailBalanceReq(AvailBalanceReq availBalanceReq) {
		this.availBalanceReq = availBalanceReq;
	}

}
