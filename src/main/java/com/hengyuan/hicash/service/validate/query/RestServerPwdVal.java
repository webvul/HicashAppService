package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.amount.AppScheduleReq;
import com.hengyuan.hicash.parameters.request.user.RestServerPwdReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 
* @author blanke.qin
* 2017年4月6日 上午10:03:21
* 类说明
 */
public class RestServerPwdVal extends RegexValidate {
	private  RestServerPwdReq restServerPwdReq;
	
	
	
	public RestServerPwdVal(RestServerPwdReq restServerPwdReq) {
		this.restServerPwdReq = restServerPwdReq;
	}

        public String validate(){
		
		//手机号
		if (RegexValidate.isNull(restServerPwdReq.getMobile())) {
			return ResultCodes.REGISTER_MOBILE_ISNULL;
		}
		
		if (!RegexValidate.isIphon(restServerPwdReq.getMobile())) {
			return ResultCodes.REGISTER_MOBILE_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}
	public RestServerPwdReq getRestServerPwdReq() {
		return restServerPwdReq;
	}

	public void setRestServerPwdReq(RestServerPwdReq restServerPwdReq) {
		this.restServerPwdReq = restServerPwdReq;
	}
	
	
}
