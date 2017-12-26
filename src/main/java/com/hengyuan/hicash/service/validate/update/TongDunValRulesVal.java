package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.TongDunValRulesReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 同盾接口验证客户黑名单，客户身份证号码、手机号码、邮箱、qq
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
public class TongDunValRulesVal {
	private TongDunValRulesReq rulesReq;

	public TongDunValRulesVal(TongDunValRulesReq rulesReq) {
		this.rulesReq = rulesReq;
	}

	public String validate() {

		/* 验证uuid */
		if (RegexValidate.isNull(rulesReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(rulesReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		/* 验证姓名 */
		if (RegexValidate.isNull(rulesReq.getAccount_name())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(rulesReq.getAccount_name())) {
			return ResultCodes.TONGDUN_VALRULES_NAME_FALSE;
		}

		/* 验证邮箱 */

		if (RegexValidate.isNull(rulesReq.getAccount_email())) {
			return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ISNULL;
		}
		if (!RegexValidate.isEmail(rulesReq.getAccount_email())) {
			return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ERROR;
		}
		
		/* 验证手机号码*/
		if(RegexValidate.isNull(rulesReq.getAccount_mobile())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(rulesReq.getAccount_mobile())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}

		

		/* 身份证号码 */
		if (RegexValidate.isNull(rulesReq.getId_number())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(rulesReq.getId_number())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
		return ResultCodes.NORMAL;
	}

	/**
	 * @return the rulesReq
	 */
	public TongDunValRulesReq getRulesReq() {
		return rulesReq;
	}

	/**
	 * @param rulesReq
	 *            the rulesReq to set
	 */
	public void setRulesReq(TongDunValRulesReq rulesReq) {
		this.rulesReq = rulesReq;
	}

}
