package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.OnlyMobileReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 手机唯一性验证  参数验证
 * 
 * @author Cary.Liu
 * @create 2014-08-11
 *
 */
public class OnlyMobileVal {

	private OnlyMobileReq mobileReq;
	
	public OnlyMobileVal(OnlyMobileReq mobileReq){
		this.mobileReq = mobileReq;
	}

	public String validate(){
		
		String resultCode = ResultCodes.NORMAL;
		
		if (RegexValidate.isNull(mobileReq.getUuid())) {
			resultCode = ResultCodes.UUIDNULL;
		} else if (!RegexValidate.isUuid(mobileReq.getUuid())) {
			resultCode = ResultCodes.UUIDILLEGAL;
		} else if (RegexValidate.isNull(mobileReq.getMobile())){
			resultCode= ResultCodes.MOBILE_ONLY_VALIDATE_ISNULL;
		} else if(!RegexValidate.isIphon(mobileReq.getMobile())){
			resultCode= ResultCodes.MOBILE_ONLY_VALIDATE_FALSE;
		}
		
		return resultCode;
	}
	
	public OnlyMobileReq getMobileReq() {
		return mobileReq;
	}

	public void setMobileReq(OnlyMobileReq mobileReq) {
		this.mobileReq = mobileReq;
	}
	
	
	
	
}
