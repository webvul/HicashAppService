package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 嗨秒贷 validate 验证用户收款银行卡信息 代理扣款
 * 
 * @author Cary.Liu
 * @createDate 2015-07-22
 *
 */
public class ProxyDeductMoneyVal {

	private ProxyDeductMoneyReq proxyReq;

	public ProxyDeductMoneyVal(ProxyDeductMoneyReq proxyReq) {
		this.proxyReq = proxyReq;
	}

	public String validate(){
		
		// 验证用户名
		if (RegexValidate.isNull(proxyReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(proxyReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		// 验证开户行
		if (RegexValidate.isNull(proxyReq.getOpenBank())) {
			return ResultCodes.PROXYDEDUCTMONEY_OPENBANK_ISNULL;
		}

		if (!RegexValidate.isOpenBank(proxyReq.getOpenBank())) {
			return ResultCodes.PROXYDEDUCTMONEY_OPENBANK_ILLEGAL;
		}
		
		// 验证银行卡
		if (RegexValidate.isNull(proxyReq.getBankCard())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isBankCard(proxyReq.getBankCard())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ILLEGAL;
		}
		
		// 扣款金额
//		if (RegexValidate.isNull(proxyReq.getAmount())) {
//			return ResultCodes.PROXYDEDUCTMONEY_AMOUNT_ISNULL;
//		}
//
//		if (!RegexValidate.isBankCard(proxyReq.getAmount())) {
//			return ResultCodes.PROXYDEDUCTMONEY_AMOUNT_ILLEGAL;
//		}
		
		return ResultCodes.NORMAL;
	}
	
	public ProxyDeductMoneyReq getProxyReq() {
		return proxyReq;
	}

	public void setProxyReq(ProxyDeductMoneyReq proxyReq) {
		this.proxyReq = proxyReq;
	}

}
