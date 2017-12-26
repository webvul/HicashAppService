package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.IsCheckBankCardReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 查询该用户名下的银行卡号是否有校验记录
 * @author jason
 * @createDate 20160804
 *
 */
public class IsCheckBankCardVal {

	private IsCheckBankCardReq checkBankCardReq;

	public IsCheckBankCardVal(IsCheckBankCardReq checkBankCardReq) {
		this.checkBankCardReq = checkBankCardReq;
	}

	public String validate(){
		
		// 验证用户名
		if (RegexValidate.isNull(checkBankCardReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(checkBankCardReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		// 验证银行卡
		if (RegexValidate.isNull(checkBankCardReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isBankCard(checkBankCardReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ILLEGAL;
		}
		return ResultCodes.NORMAL;
	}

	/**
	 * @return the checkBankCardReq
	 */
	public IsCheckBankCardReq getSendCodeReq() {
		return checkBankCardReq;
	}

	/**
	 * @param checkBankCardReq the checkBankCardReq to set
	 */
	public void setSendCodeReq(IsCheckBankCardReq checkBankCardReq) {
		this.checkBankCardReq = checkBankCardReq;
	}
	
}
