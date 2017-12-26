package com.hengyuan.hicash.service.validate.query;

import org.apache.log4j.Logger;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.param.LoanAmtCalReq;
import com.hengyuan.hicash.utils.RecordUtils;
import com.hengyuan.hicash.utils.RegexValidate;

public class RepayProgramVal extends RegexValidate {
	
	private static Logger logger = Logger.getLogger(RepayProgramVal.class);
	
	private LoanAmtCalReq loanAmtCalReq;
	
	public RepayProgramVal(LoanAmtCalReq loanAmtCalReq){
		this.loanAmtCalReq = loanAmtCalReq;
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(loanAmtCalReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (RegexValidate.isNull(loanAmtCalReq.getCustType())) {
			return ResultCodes.CUSTTYPE_ERROR_ISNULL;
		}
		
		if (RegexValidate.isNull(loanAmtCalReq.getIndustryCode())) {
			return ResultCodes.INDUSTRY_CODE_ISNULL;
		}
	
		if (RegexValidate.isNull(loanAmtCalReq.getMerProId())) {
			return ResultCodes.PROID_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isOnlyNum(loanAmtCalReq.getMerProId())) {
			return ResultCodes.PROID_ERROR_ILLEGAL;
		}
		
		if (RegexValidate.isNull(loanAmtCalReq.getSupplierId())) {
			return ResultCodes.SUPPLIERID_ISNULL;
		}
		if (RegexValidate.isNull(loanAmtCalReq.getSaleSiteId())) {
			return ResultCodes.CREATEAPPPAY_SITEID_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}

	public LoanAmtCalReq getLoanAmtCalReq() {
		return loanAmtCalReq;
	}

	public void setLoanAmtCalReq(LoanAmtCalReq loanAmtCalReq) {
		this.loanAmtCalReq = loanAmtCalReq;
	}
	
}
