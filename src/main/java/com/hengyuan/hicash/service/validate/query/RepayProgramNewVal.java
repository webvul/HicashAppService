package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalNewReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class RepayProgramNewVal extends RegexValidate {

	private LoanAmtCalNewReq loanAmtCalNewReq;

	public RepayProgramNewVal(LoanAmtCalNewReq loanAmtCalNewReq) {
		this.loanAmtCalNewReq = loanAmtCalNewReq;
	}

	public String validate() {

		if (RegexValidate.isNull(loanAmtCalNewReq.getIndustryCode())) {
			return ResultCodes.INDUSTRY_CODE_ISNULL;
		}

//		if (RegexValidate.isNull(loanAmtCalNewReq.getUserName())) {
//			return ResultCodes.USER_ERROR_ISNULL;
//		}

		if ("THFQ".equals(loanAmtCalNewReq.getIndustryCode())) {
			if (RegexValidate.isNull(loanAmtCalNewReq.getApplyCount())) {
				return ResultCodes.APPLY_COUNT_ISNULL;
			}
		}

		return ResultCodes.NORMAL;
	}

	public LoanAmtCalNewReq getLoanAmtCalNewReq() {
		return loanAmtCalNewReq;
	}

	public void setLoanAmtCalNewReq(LoanAmtCalNewReq loanAmtCalNewReq) {
		this.loanAmtCalNewReq = loanAmtCalNewReq;
	}

}
