package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.LoanPayReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class LoanPayVal {

	private LoanPayReq loanPayReq;

	public LoanPayVal(LoanPayReq loanPayReq) {
		this.loanPayReq = loanPayReq;
	}

	public String validate() {

		if (RegexValidate.isNull(loanPayReq.getIndustryCode())) {
			return ResultCodes.INDUSTRY_CODE_ISNULL;
		}
		if (RegexValidate.isNull(loanPayReq.getAmount())) {
			return ResultCodes.PROXYDEDUCTMONEY_AMOUNT_ISNULL;
		}
		if (RegexValidate.isNull(loanPayReq.getDays())) {
			return ResultCodes.CREATEAPPPAY_LOANPRODUCT_ISNULL;
		}

		return ResultCodes.NORMAL;
	}

	public LoanPayReq getLoanPayReq() {
		return loanPayReq;
	}

	public void setLoanPayReq(LoanPayReq loanPayReq) {
		this.loanPayReq = loanPayReq;
	}

}
