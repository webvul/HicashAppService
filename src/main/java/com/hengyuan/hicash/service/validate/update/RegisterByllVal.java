package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.RegisterByllReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 蓝领活动注册 validate
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterByllVal {

	private RegisterByllReq registerReq;

	public RegisterByllVal(RegisterByllReq registerReq) {
		this.registerReq = registerReq;
	}
	
	public String validate(){
		
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
		
		/* 邀请码 */
		if (RegexValidate.isNull(registerReq.getInviteCode())) {
			return ResultCodes.REGISTER_INVITECODE_ISNULL;
		}

		if (!RegexValidate.isNumber(registerReq.getInviteCode())) {
			return ResultCodes.REGISTER_INVITECODE_ILLEGAL;
		}
		
		/* 门店号 */
		if (RegexValidate.isNull(registerReq.getStoreCode())) {
			return ResultCodes.REGISTER_STORECODE_ISNULL;
		}

//		if (!RegexValidate.isNumber(registerReq.getStoreCode())) {
//			return ResultCodes.REGISTER_STORECODE_ILLEGAL;
//		}
		
		/* 图片url */
		if (RegexValidate.isNull(registerReq.getUserScenepicUrl())) {
			return ResultCodes.REGISTER_PICURL_ISNULL;
		}

		if (RegexValidate.isNull(registerReq.getUserScenepicThumUrl())) {
			return ResultCodes.REGISTER_PICURL_ISNULL;
		}
		
		
		return ResultCodes.NORMAL;
	}

	public RegisterByllReq getRegisterReq() {
		return registerReq;
	}

	public void setRegisterReq(RegisterByllReq registerReq) {
		this.registerReq = registerReq;
	}

}
