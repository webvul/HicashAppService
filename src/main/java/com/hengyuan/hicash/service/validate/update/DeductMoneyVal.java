package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.DeductMoneyReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 扣款 validate
 * 
 * @author cary.Liu
 * @createDate 2015-08-27
 *
 */
public class DeductMoneyVal {

	private DeductMoneyReq req;

	public DeductMoneyVal(DeductMoneyReq req) {
		this.req = req;
	}

	public String validate(){
		
		// 验证用户名
		if (RegexValidate.isNull(req.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(req.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		// 验证开户行
		if (RegexValidate.isNull(req.getOpenBank())) {
			return ResultCodes.PROXYDEDUCTMONEY_OPENBANK_ISNULL;
		}

		if (!RegexValidate.isOpenBank(req.getOpenBank())) {
			return ResultCodes.PROXYDEDUCTMONEY_OPENBANK_ILLEGAL;
		}
		
		// 验证银行卡
		if (RegexValidate.isNull(req.getBankCard())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isBankCard(req.getBankCard())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ILLEGAL;
		}
		
		// 扣款金额
		if (RegexValidate.isNull(req.getAmount())) {
			return ResultCodes.PROXYDEDUCTMONEY_AMOUNT_ISNULL;
		}

		if (!RegexValidate.isDigitPos(req.getAmount())) {
			return ResultCodes.PROXYDEDUCTMONEY_AMOUNT_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public DeductMoneyReq getReq() {
		return req;
	}

	public void setReq(DeductMoneyReq req) {
		this.req = req;
	}

}
