package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.amount.RepaymentTotalAmountReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 还款总额（近七天） validate
 * 
 * @author Cary.Liu
 * @createDate 2015-10-15
 *
 */
public class RepaymentTotalAmountVal {

	private RepaymentTotalAmountReq totalReq;

	public RepaymentTotalAmountVal(RepaymentTotalAmountReq totalReq) {
		this.totalReq = totalReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(totalReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(totalReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(totalReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}
		
		if (!RegexValidate.isUsername(totalReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}

	public RepaymentTotalAmountReq getTotalReq() {
		return totalReq;
	}

	public void setTotalReq(RepaymentTotalAmountReq totalReq) {
		this.totalReq = totalReq;
	}

}
