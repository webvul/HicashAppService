package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UpdateUserCardReq;
import com.hengyuan.hicash.utils.RegexValidate;


/**
 * 更新用户银行卡信息
 * @author jaosn.wei	
 * @createDate 20160824
 *
 */
public class UpdateUserCardVal {

	private UpdateUserCardReq updateUserCardReq;

	public UpdateUserCardVal(UpdateUserCardReq updateUserCardReq) {
		this.updateUserCardReq = updateUserCardReq;
	}

	public String validate(){
		
		// 验证流水号
		if (RegexValidate.isNull(updateUserCardReq.getAppNo())) {
			return ResultCodes.STU_UPDATE_APPNO_NOTNULL;
		}
		if(!RegexValidate.isNumber(updateUserCardReq.getAppNo())||!RegexValidate.isLength(updateUserCardReq.getAppNo(), 14,14)){
			return ResultCodes.STU_UPDATE_APPNO_FAIL;
		}
		
		// 验证用户名
		if (RegexValidate.isNull(updateUserCardReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(updateUserCardReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		// 验证银行卡
		if (RegexValidate.isNull(updateUserCardReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isBankCard(updateUserCardReq.getAccountNo())) {
			return ResultCodes.PROXYDEDUCTMONEY_BANKCARD_ILLEGAL;
		}
		/* 真实姓名 */
		if (RegexValidate.isNull(updateUserCardReq.getAccountName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(updateUserCardReq.getAccountName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}
		/* 银行编码不能为空*/
		if (RegexValidate.isNull(updateUserCardReq.getBankCode())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户省份不能为空*/
		if (RegexValidate.isNull(updateUserCardReq.getDkProvince())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户城市不能为空*/
		if (RegexValidate.isNull(updateUserCardReq.getDkCity())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 开户区不能为空*/
		if (RegexValidate.isNull(updateUserCardReq.getDkAreaCode())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		/* 支行不能为空*/
		if (RegexValidate.isNull(updateUserCardReq.getDkBankBranch())) {
			return ResultCodes.CMBC_IDENTIFY_CONFIRM_BANKNAME_ISNULL;
		}
		return ResultCodes.NORMAL;
	}

	/**
	 * @return the updateUserCardReq
	 */
	public UpdateUserCardReq getSendCodeReq() {
		return updateUserCardReq;
	}

	/**
	 * @param updateUserCardReq the updateUserCardReq to set
	 */
	public void setSendCodeReq(UpdateUserCardReq updateUserCardReq) {
		this.updateUserCardReq = updateUserCardReq;
	}
	
}
