package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.CancelCreditAndLimitReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CreditApplyVal {

	private CancelCreditAndLimitReq cancelCreditAndLimitReq;

	public CreditApplyVal(CancelCreditAndLimitReq cancelCreditAndLimitReq) {
		this.cancelCreditAndLimitReq = cancelCreditAndLimitReq;
	}

	public String validate() {
		
		if (RegexValidate.isNull(cancelCreditAndLimitReq.getApp_no())) {
			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
		}
		return ResultCodes.NORMAL;
	}

	public CancelCreditAndLimitReq getCancelCreditAndLimitReq() {
		return cancelCreditAndLimitReq;
	}

	public void setCancelCreditAndLimitReq(
			CancelCreditAndLimitReq cancelCreditAndLimitReq) {
		this.cancelCreditAndLimitReq = cancelCreditAndLimitReq;
	}

	

}
