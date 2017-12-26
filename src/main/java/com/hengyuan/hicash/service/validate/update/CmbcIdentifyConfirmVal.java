package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifyConfirmReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 民生银行代扣业务身份认证-用于验证交易用户验证交易码。CP0030
 * 
 * @author LiHua.Ren
 * @create date 2015-12-02
 */
public class CmbcIdentifyConfirmVal {

	private CmbcIdentifyConfirmReq confirmReq;

	public CmbcIdentifyConfirmVal(CmbcIdentifyConfirmReq confirmReq) {
		this.confirmReq = confirmReq;
	}

	public String validate(){
		
		// 验证用户名
		if (RegexValidate.isNull(confirmReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(confirmReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		
		
		// 验证银行卡
		if (RegexValidate.isNull(confirmReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isBankCard(confirmReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ILLEGAL;
		}
		// 验证手机号码

		if(RegexValidate.isNull(confirmReq.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(confirmReq.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}
		/* 真实姓名 */
		if (RegexValidate.isNull(confirmReq.getAccountName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(confirmReq.getAccountName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}

		/* 身份证号码 */
		if (RegexValidate.isNull(confirmReq.getCertNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(confirmReq.getCertNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}

		/* 手机令牌 */
		if (RegexValidate.isNull(confirmReq.getPhoneToken())) {
			return ResultCodes.CMBC_IDENTIFYCONFIRM_PHONE_TOKEN_NOTNULL;
		}
		/* 手机验证码 */
		if (RegexValidate.isNull(confirmReq.getPhoneVerCode())) {
			return ResultCodes.REGISTER_IDENTIFYCODE_ISNULL;
		}
		/* 银行编码不能为空*/
		if (RegexValidate.isNull(confirmReq.getBankCode())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户省份不能为空*/
		if (RegexValidate.isNull(confirmReq.getDkProvince())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户城市不能为空*/
		if (RegexValidate.isNull(confirmReq.getDkCity())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户区不能为空*/
		if (RegexValidate.isNull(confirmReq.getDkAreaCode())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 支行不能为空*/
		if (RegexValidate.isNull(confirmReq.getDkBankBranch())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		
	
		return ResultCodes.NORMAL;
	}

	/**
	 * @return the confirmReq
	 */
	public CmbcIdentifyConfirmReq getConfirmReq() {
		return confirmReq;
	}

	/**
	 * @param confirmReq the confirmReq to set
	 */
	public void setConfirmReq(CmbcIdentifyConfirmReq confirmReq) {
		this.confirmReq = confirmReq;
	}

	
}
