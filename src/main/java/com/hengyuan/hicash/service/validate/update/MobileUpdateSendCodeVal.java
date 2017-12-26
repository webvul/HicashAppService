package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.MobileUpdateSendCodeReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class MobileUpdateSendCodeVal extends RegexValidate{
	
	private MobileUpdateSendCodeReq mobileUpdateReq;
	
	public MobileUpdateSendCodeVal(MobileUpdateSendCodeReq mobileUpdateReq){
		
		this.mobileUpdateReq = mobileUpdateReq;
		
	}
	
	public String validate() {
		
		if (RegexValidate.isNull(mobileUpdateReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(mobileUpdateReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(mobileUpdateReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(mobileUpdateReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		if (RegexValidate.isNull(mobileUpdateReq.getOldMobile())) {
			return ResultCodes.UPDATE_OLD_MOBILE_ERROR;
		}
		
		if (RegexValidate.isIphon(mobileUpdateReq.getOldMobile())==false) {
			return ResultCodes.UPDATE_OLD_MOBIL_ERROR_EXIST;
		}
		
		if (RegexValidate.isNull(mobileUpdateReq.getNewMobile())) {
			return ResultCodes.UPDATE_NEW_MOBILE_ERROR;
		}
		
		if (RegexValidate.isIphon(mobileUpdateReq.getNewMobile())==false) {
			return ResultCodes.UPDATE_NEW_MOBIL_ERROR_EXIST;
		}
		
		return ResultCodes.NORMAL;
	}

	public MobileUpdateSendCodeReq getMobileUpdateReq() {
		return mobileUpdateReq;
	}

	public void setMobileUpdateReq(MobileUpdateSendCodeReq mobileUpdateReq) {
		this.mobileUpdateReq = mobileUpdateReq;
	}
}
