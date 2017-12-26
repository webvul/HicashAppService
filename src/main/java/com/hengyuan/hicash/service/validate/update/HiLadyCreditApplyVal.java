package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.mktApp.HilLadyCancelCreditAndLimitReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class HiLadyCreditApplyVal {

	private HilLadyCancelCreditAndLimitReq hilLadyCancelCreditAndLimitReq;

	public HiLadyCreditApplyVal(HilLadyCancelCreditAndLimitReq hilLadyCancelCreditAndLimitReq) {
		this.hilLadyCancelCreditAndLimitReq = hilLadyCancelCreditAndLimitReq;
	}

	public String validate() {
		
		if (RegexValidate.isNull(hilLadyCancelCreditAndLimitReq.getApp_no())) {
			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
		}
		return ResultCodes.NORMAL;
	}

	public HilLadyCancelCreditAndLimitReq getHilLadyCancelCreditAndLimitReq() {
		return hilLadyCancelCreditAndLimitReq;
	}

	public void setHilLadyCancelCreditAndLimitReq(
			HilLadyCancelCreditAndLimitReq hilLadyCancelCreditAndLimitReq) {
		this.hilLadyCancelCreditAndLimitReq = hilLadyCancelCreditAndLimitReq;
	}


	

}
