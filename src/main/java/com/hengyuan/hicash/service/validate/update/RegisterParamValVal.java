package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class RegisterParamValVal {

	private RegisterReq registerReq;

	public RegisterParamValVal(RegisterReq registerReq) {
		this.registerReq = registerReq;
	}

	public String validate() {

		if (RegexValidate.isNull(registerReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(registerReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		/* 验证居住城市 */
//		if (RegexValidate.isNull(registerReq.getLiveCity())) {
//			return ResultCodes.REGISTER_LIVECITY_ISNULL;
//		}
//
//		if (!RegexValidate.isSelectToAddress(registerReq.getLiveCity())) {
//			return ResultCodes.REGISTER_LIVECITY_ILLEGAL;
//		}

		/* 验证手机号码 */
		if (RegexValidate.isNull(registerReq.getMobileNo())) {
			return ResultCodes.REGISTER_MOBILE_ISNULL;
		}

		if (!RegexValidate.isIphon(registerReq.getMobileNo())) {
			return ResultCodes.REGISTER_MOBILE_ILLEGAL;
		}

		/* 验证码 */
		if (RegexValidate.isNull(registerReq.getIdentifyingCode())) {
			return ResultCodes.REGISTER_IDENTIFYCODE_ISNULL;
		}

		/* 验证密码 */
		if (RegexValidate.isNull(registerReq.getPassWord())) {
			return ResultCodes.REGISTER_PASSWORD_ISNULL;
		}

		if (RegexValidate.isChinese(registerReq.getPassWord())) {
			return ResultCodes.REGISTER_PASSWORD_ILLEGAL;
		}

		/* 真实姓名 */
		if (RegexValidate.isNull(registerReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(registerReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}

		/* 身份证号码 */
		if (RegexValidate.isNull(registerReq.getIdCard())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(registerReq.getIdCard())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}

		return ResultCodes.NORMAL;
	}

	public RegisterReq getRegisterReq() {
		return registerReq;
	}

	public void setRegisterReq(RegisterReq registerReq) {
		this.registerReq = registerReq;
	}
	
}
