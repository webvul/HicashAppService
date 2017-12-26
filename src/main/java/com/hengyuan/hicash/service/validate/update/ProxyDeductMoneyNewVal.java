package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ProxyDeductMoneyNewReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 嗨秒贷 req 验证用户收款银行卡信息 代收 快捷鉴权
 * 
 * @author LiHua.Ren
 * @createDate 2015-11-03
 *
 */
public class ProxyDeductMoneyNewVal {

	private ProxyDeductMoneyNewReq proxyReq;

	public ProxyDeductMoneyNewVal(ProxyDeductMoneyNewReq proxyReq) {
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
		// 验证手机号码

		if(RegexValidate.isNull(proxyReq.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(proxyReq.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
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
	
	public ProxyDeductMoneyNewReq getProxyReq() {
		return proxyReq;
	}

	public void setProxyReq(ProxyDeductMoneyNewReq proxyReq) {
		this.proxyReq = proxyReq;
	}

}
