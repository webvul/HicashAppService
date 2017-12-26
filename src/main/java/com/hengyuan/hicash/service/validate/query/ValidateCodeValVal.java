package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ValidateCodeValReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 忘记密码-验证短信验证码验证 validate
 * 
 * @author Cary.Liu
 * @createDate 2015-06-02
 *
 */
public class ValidateCodeValVal {

	private ValidateCodeValReq valReq;

	public ValidateCodeValVal(ValidateCodeValReq valReq) {
		this.valReq = valReq;
	}

	public String validate(){
		
		if (RegexValidate.isNull(valReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(valReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if(RegexValidate.isNull(valReq.getMobileNo())){
			return ResultCodes.SENDPSWCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(valReq.getMobileNo())){
			return ResultCodes.SENDPSWCODE_MOBILE_ILLEGAL;
		}
		
		/* 验证码 */
		if(RegexValidate.isNull(valReq.getValidateCode())){
			return ResultCodes.VALIDATEPSWCODE_VALIDATECODE_ISNULL;
		}
		
		return ResultCodes.NORMAL;
	}
	
	public ValidateCodeValReq getValReq() {
		return valReq;
	}

	public void setValReq(ValidateCodeValReq valReq) {
		this.valReq = valReq;
	}

}
