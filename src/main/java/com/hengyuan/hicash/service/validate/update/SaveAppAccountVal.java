package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SaveAppAccountReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 保存申请件账户资料 validate 提交申请（蓝领数码业务）
 * 
 * @author Cary.Liu
 * @createDate 2016-01-26
 *
 */
public class SaveAppAccountVal {

	private SaveAppAccountReq reqParam;

	public SaveAppAccountVal(SaveAppAccountReq reqParam) {
		this.reqParam = reqParam;
	}

	public String validate(){
		
		//验证用户名
		if (RegexValidate.isNull(reqParam.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(reqParam.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
				
		// 申请件号
		if (RegexValidate.isNull(reqParam.getAppNo())) {
			return ResultCodes.SAVEAPPACCT_APPNO_ISNULL;
		}

		if (!RegexValidate.isAppNo(reqParam.getAppNo())) {
			return ResultCodes.SAVEAPPACCT_APPNO_ILLEGAL;
		}
				
		/* 开户行 */
		if (RegexValidate.isNull(reqParam.getOpenBank())) {
			return ResultCodes.SAVEAPPACCT_OPENBAN_ISNULL;
		}

		if (!RegexValidate.isStuId(reqParam.getOpenBank())) {
			return ResultCodes.SAVEAPPACCT_OPENBAN_ILLEGAL;
		}

		/* 银行卡号 */
		if (RegexValidate.isNull(reqParam.getBankCard())) {
			return ResultCodes.SAVEAPPACCT_BANKCARD_ISNULL;
		}

		if (!RegexValidate.isLongNumber(reqParam.getBankCard())) {
			return ResultCodes.SAVEAPPACCT_BANKCARD_ILLEGAL;
		}

		/* 是否开通代扣 */
		if (RegexValidate.isNull(reqParam.getIsOpenProxy())) {
			return ResultCodes.SAVEAPPACCT_BANKSYN_ISNULL;
		}

		if (!RegexValidate.isSysMark(reqParam.getIsOpenProxy())) {
			return ResultCodes.SAVEAPPACCT_BANKSYN_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public SaveAppAccountReq getReqParam() {
		return reqParam;
	}

	public void setReqParam(SaveAppAccountReq reqParam) {
		this.reqParam = reqParam;
	}

}
