package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CmbcIdentifySendCodeReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 民生银行代扣银行卡验证CP0032
 * 发送验证码
 * @author Lihua.Ren
 * @createDate 2015-12-01
 *
 */
public class CmbcIdentifySendCodeVal {

	private CmbcIdentifySendCodeReq sendCodeReq;

	public CmbcIdentifySendCodeVal(CmbcIdentifySendCodeReq sendCodeReq) {
		this.sendCodeReq = sendCodeReq;
	}

	public String validate(){
		
		// 验证用户名
		if (RegexValidate.isNull(sendCodeReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(sendCodeReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		
		
		// 验证银行卡
		if (RegexValidate.isNull(sendCodeReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isBankCard(sendCodeReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ILLEGAL;
		}
		// 验证手机号码

		if(RegexValidate.isNull(sendCodeReq.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(sendCodeReq.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}
		/* 真实姓名 */
		if (RegexValidate.isNull(sendCodeReq.getAccountName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(sendCodeReq.getAccountName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}

		/* 身份证号码 */
		if (RegexValidate.isNull(sendCodeReq.getCertNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(sendCodeReq.getCertNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
		/* 银行编码不能为空*/
		if (RegexValidate.isNull(sendCodeReq.getBankCode())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户省份不能为空*/
		if (RegexValidate.isNull(sendCodeReq.getDkProvince())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户城市不能为空*/
		if (RegexValidate.isNull(sendCodeReq.getDkCity())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户区不能为空*/
		if (RegexValidate.isNull(sendCodeReq.getDkAreaCode())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 支行不能为空*/
		if (RegexValidate.isNull(sendCodeReq.getDkBankBranch())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		return ResultCodes.NORMAL;
	}

	/**
	 * @return the sendCodeReq
	 */
	public CmbcIdentifySendCodeReq getSendCodeReq() {
		return sendCodeReq;
	}

	/**
	 * @param sendCodeReq the sendCodeReq to set
	 */
	public void setSendCodeReq(CmbcIdentifySendCodeReq sendCodeReq) {
		this.sendCodeReq = sendCodeReq;
	}
	
}
