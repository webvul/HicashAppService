package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ForgetPassWordReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class ForgetPassWordVal extends RegexValidate {

	private ForgetPassWordReq forgetPassWordReq;
	
	public ForgetPassWordVal(ForgetPassWordReq forgetPassWordReq){
		
		this.forgetPassWordReq = forgetPassWordReq;
		
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(forgetPassWordReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(forgetPassWordReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		/*手机号码*/
		if (RegexValidate.isNull(forgetPassWordReq.getMobile())) {
			return ResultCodes.FORGETPASSWORd_PHONE_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isIphon(forgetPassWordReq.getMobile())) {
			return ResultCodes.FORGETPASSWORd_PHONE_ERROR;
		}
		
		/*验证码*/
		if (RegexValidate.isNull(forgetPassWordReq.getValidateCode())) {
			return ResultCodes.FORGETPASSWORd_CODE_NOTNULL;
		}
		
		if (!RegexValidate.isDigitNotNeg(forgetPassWordReq.getValidateCode())) {
			return ResultCodes.FORGET_PASSWORd_CODE_FALSE;
		}
		
		if (!RegexValidate.isLength(forgetPassWordReq.getValidateCode(),1,6)) {
			return ResultCodes.FORGET_PASSWORd_CODE_LENGTH;
		}

		return ResultCodes.NORMAL;
	}

	public ForgetPassWordReq getForgetPassWordReq() {
		return forgetPassWordReq;
	}

	public void setForgetPassWordReq(ForgetPassWordReq forgetPassWordReq) {
		this.forgetPassWordReq = forgetPassWordReq;
	}
}
