package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyRechargeReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 嗨秒贷 val 验证用户收款银行卡信息 代收 快捷充值
 * 
 * @author LiHua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyRechargeVal {

	private ProxyDeductMoneyRechargeReq proxyReq;

	public ProxyDeductMoneyRechargeVal(ProxyDeductMoneyRechargeReq proxyReq) {
		this.proxyReq = proxyReq;
	}

	public String validate() {

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
		// 验证客户号
		if (RegexValidate.isNull(proxyReq.getCustId())) {
			return ResultCodes.PROXY_DEDUCTMONEY_RECHARGE_CUSTID_ISNULL;
		}
		// 验证手机令牌
		if (RegexValidate.isNull(proxyReq.getPhoneToken())) {
			return ResultCodes.PROXY_DEDUCTMONEY_RECHARGE_PHONETOKEN_ISNULL;
		}
		// 验证订单号
		if (RegexValidate.isNull(proxyReq.getMerOrderId())) {
			return ResultCodes.PROXY_DEDUCTMONEY_RECHARGE_ORDERID_ISNULL;
		}
		// 验证手机验证码
		if (RegexValidate.isNull(proxyReq.getPhoneVerCode())) {
			return ResultCodes.PROXY_DEDUCTMONEY_RECHARGE_PHONECODE_ISNULL;
		}

		return ResultCodes.NORMAL;
	}

	public ProxyDeductMoneyRechargeReq getProxyReq() {
		return proxyReq;
	}

	public void setProxyReq(ProxyDeductMoneyRechargeReq proxyReq) {
		this.proxyReq = proxyReq;
	}

}
