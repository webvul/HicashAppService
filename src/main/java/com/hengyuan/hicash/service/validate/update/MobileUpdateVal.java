package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.MobileUpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class MobileUpdateVal extends RegexValidate {

	private MobileUpdateReq mobileUpdateReq;
	
	public MobileUpdateVal(MobileUpdateReq mobileUpdateReq){
		
		this.mobileUpdateReq  = mobileUpdateReq;
		
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(mobileUpdateReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(mobileUpdateReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//用户名
		if (RegexValidate.isNull(mobileUpdateReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(mobileUpdateReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		//原手机号码
		if (RegexValidate.isNull(mobileUpdateReq.getOldMobile())) {
			return ResultCodes.UPDATE_OLD_MOBILE_ERROR;
		}
		
		if (!RegexValidate.isIphon(mobileUpdateReq.getOldMobile())) {
			return ResultCodes.UPDATE_OLD_MOBIL_ERROR_EXIST;
		}
		
		//新手机号码
		if (RegexValidate.isNull(mobileUpdateReq.getNewMobile())) {
			return ResultCodes.UPDATE_NEW_MOBILE_ERROR;
		}
		
		if (!RegexValidate.isIphon(mobileUpdateReq.getNewMobile())) {
			return ResultCodes.UPDATE_NEW_MOBIL_ERROR_EXIST;
		}
		
		/*验证码*/
		if (RegexValidate.isNull(mobileUpdateReq.getCertificationCode())) {
			return ResultCodes.UPDATE_NEW_MOBIL_CERTIFICATIONCODE_EXIST;
		}
		
		if (!RegexValidate.isDigitNotNeg(mobileUpdateReq.getCertificationCode())) {
			return ResultCodes.UPDATE_NEW_MOBIL_CERTIFICATIONCODE_ERROR;
		}
		
		if (!RegexValidate.isLength(mobileUpdateReq.getCertificationCode(),1,6)) {
			return ResultCodes.UPDATE_NEW_MOBIL_CERTIFICATIONCODE_LENGTH;
		}
		
		
		return ResultCodes.NORMAL;
	}

	public MobileUpdateReq getMobileUpdateReq() {
		return mobileUpdateReq;
	}

	public void setMobileUpdateReq(MobileUpdateReq mobileUpdateReq) {
		this.mobileUpdateReq = mobileUpdateReq;
	}
	
}
