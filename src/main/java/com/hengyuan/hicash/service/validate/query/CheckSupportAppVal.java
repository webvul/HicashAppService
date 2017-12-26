package com.hengyuan.hicash.service.validate.query;

import java.math.BigDecimal;

import com.hengyuan.hicash.constant.Consts;
import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CheckSupportAppReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CheckSupportAppVal extends RegexValidate {
	
	private CheckSupportAppReq checkSupportAppReq;
	
	public CheckSupportAppVal(CheckSupportAppReq checkSupportAppReq){
		
		this.checkSupportAppReq = checkSupportAppReq;
		
	}

	public String validate(){
		
		if (RegexValidate.isNull(checkSupportAppReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(checkSupportAppReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		if (RegexValidate.isNull(checkSupportAppReq.getUser_name())) {
			return ResultCodes.USERNAME_NOT_NULL;
		}
		if (RegexValidate.isNull(checkSupportAppReq.getMobile())) {
			return ResultCodes.SENDPSWCODE_MOBILE_ISNULL;
		}
		
		if (!RegexValidate.isIphon(checkSupportAppReq.getMobile())) {
			return ResultCodes.SENDPSWCODE_MOBILE_ILLEGAL;
		}

		if (RegexValidate.isNull(checkSupportAppReq.getCustType())) {
			return ResultCodes.CREATEAPPPAY_CUSTTYPE_ISNULL;
		}

		if (RegexValidate.isNull(checkSupportAppReq.getIndustryCode())) {
			return ResultCodes.INDUSTRY_CODE_ISNULL;
		}

		if (RegexValidate.isNull(checkSupportAppReq.getIs_type())) {
			return ResultCodes.CARD_TYPE_NOTNULL;
		}
		
		if(checkSupportAppReq.getIndustryCode().equals(Consts.INDUSTRY_VIPD)){
			
			if (RegexValidate.isNull(checkSupportAppReq.getPeriods())) {
				return ResultCodes.CREATEAPPPAY_LOANPRODUCT_ISNULL;
			}
			
			try {
				if (Integer.valueOf(checkSupportAppReq.getPeriods()) == 0){
					return ResultCodes.PERIODS_IS_ZERO;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCodes.CREATEAPPPAY_LOANPRODUCT_ILLEGAL;
			}
			
			if (RegexValidate.isNull(checkSupportAppReq.getTranPrice())) {
				return ResultCodes.PROXYDEDUCTMONEY_AMOUNT_ISNULL;
			}
			try {
				if (Double.valueOf(checkSupportAppReq.getTranPrice()) <= 0){
					return ResultCodes.AMOUNT_IS_TOO_SMALL;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return ResultCodes.APPLYAMOUNT_ERROR_CANTCHAR;
			}
			
			if (RegexValidate.isNull(checkSupportAppReq.getLoanProductId())) {
				return ResultCodes.LOANPRODUCT_ERROR_ISNULL;
			}
			
		}

		return ResultCodes.NORMAL;
	}

	public CheckSupportAppReq getCheckSupportAppReq() {
		return checkSupportAppReq;
	}

	public void setCheckSupportAppReq(CheckSupportAppReq checkSupportAppReq) {
		this.checkSupportAppReq = checkSupportAppReq;
	}
	

}
