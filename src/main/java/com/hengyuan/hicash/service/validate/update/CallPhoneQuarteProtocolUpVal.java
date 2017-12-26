package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CallPhoneQuartetProtocalUpReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 	电信签订四方协议,修改用户的email,验证类
 * 
 * @author lihua.Ren
 * @create date 2015-08-20
 *
 */
public class CallPhoneQuarteProtocolUpVal{
	private CallPhoneQuartetProtocalUpReq infoReq;

public CallPhoneQuarteProtocolUpVal(CallPhoneQuartetProtocalUpReq infoReq) {
	this.infoReq = infoReq;
}

public String validate() {

	/* 验证uuid */
	if (RegexValidate.isNull(infoReq.getUuid())) {
		return ResultCodes.UUIDNULL;
	}

	if (!RegexValidate.isUuid(infoReq.getUuid())) {
		return ResultCodes.UUIDILLEGAL;
	}

	/* 验证用户名 */
	if (RegexValidate.isNull(infoReq.getUserName())) {
		return ResultCodes.USER_ERROR_ISNULL;
	}

	if (!RegexValidate.isUsername(infoReq.getUserName())) {
		return ResultCodes.USER_NAME_ERROR_CANTCHAR;
	}
	/* 验证邮箱 */
	if (RegexValidate.isNull(infoReq.getEmail())) {
		return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ISNULL;
	}
	if (!RegexValidate.isEmail(infoReq.getEmail())) {
		return ResultCodes.CALL_PHONE_QUARTET_PROTOCOL_EMAIL_ERROR;
	}
	

	return ResultCodes.NORMAL;
}

public CallPhoneQuartetProtocalUpReq getInfoReq() {
	return infoReq;
}

public void setInfoReq(CallPhoneQuartetProtocalUpReq infoReq) {
	this.infoReq = infoReq;
}


}
