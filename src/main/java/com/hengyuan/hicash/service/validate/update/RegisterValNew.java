package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.RegisterReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class RegisterValNew extends RegexValidate{
	private RegisterReq registerReq;

	public RegisterValNew(RegisterReq registerReq) {
		this.registerReq = registerReq;
	}

	public String validate() {

		if (RegexValidate.isNull(registerReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(registerReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		/* 验证客户类型 */
		if (RegexValidate.isNull(registerReq.getCustType())) {
			return ResultCodes.REGISTER_CUSTTYPE_ISNULL;
		}

		if (!RegexValidate.isCustType(registerReq.getCustType())) {
			return ResultCodes.REGISTER_CUSTTYPE_ILLEGAL;
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

		/*if (!RegexValidate.isIdCard(registerReq.getIdCard())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}*/

		return ResultCodes.NORMAL;
	}

	public RegisterReq getRegisterReq() {
		return registerReq;
	}

	public void setRegisterReq(RegisterReq registerReq) {
		this.registerReq = registerReq;
	}

}
