package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SendPswCodeReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 忘记密码-发送验证码 validate
 * 
 * @author Cary.Liu
 * @createDate 2015-06-12
 *
 */
public class SendPswCodeVal {

	private SendPswCodeReq codeReq;

	public SendPswCodeVal(SendPswCodeReq codeReq) {
		this.codeReq = codeReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(codeReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(codeReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		// 手机号码
		if (RegexValidate.isNull(codeReq.getMobileNo())) {
			return ResultCodes.SENDPSWCODE_MOBILE_ISNULL;
		}

		if (!RegexValidate.isIphon(codeReq.getMobileNo())) {
			return ResultCodes.SENDPSWCODE_MOBILE_ILLEGAL;
		}

		return ResultCodes.NORMAL;
	}

	public SendPswCodeReq getCodeReq() {
		return codeReq;
	}

	public void setCodeReq(SendPswCodeReq codeReq) {
		this.codeReq = codeReq;
	}

}
