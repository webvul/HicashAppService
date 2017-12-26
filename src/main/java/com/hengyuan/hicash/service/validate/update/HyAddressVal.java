package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ForgetPassWordReq;
import com.hengyuan.hicash.parameters.request.user.HyAddressReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class HyAddressVal extends RegexValidate {
	
	
    private HyAddressReq hyAddressReq;
	
	public HyAddressVal(HyAddressReq hyAddressReq){
		
		this.hyAddressReq = hyAddressReq;
		
	}
	
	public String validate() {
		
		/* 姓名 */
		if (RegexValidate.isNull(hyAddressReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}
		if (!RegexValidate.isRealName(hyAddressReq.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}
		//用户名
		if (RegexValidate.isNull(hyAddressReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(hyAddressReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		/* 手机号码 */
		if(RegexValidate.isNull(hyAddressReq.getMobile())){
			return ResultCodes.REGISTER_MOBILE_ISNULL;
		}
		if(!RegexValidate.isIphon(hyAddressReq.getMobile())){
			return ResultCodes.REGISTER_MOBILE_ILLEGAL;
		}		
		/*邮寄地址*/
		if (RegexValidate.isNull(hyAddressReq.getEmailAddress())) {
			return ResultCodes.CUST_REC_ADRESS_DETAIL_NOTNULL;
		}
		if (!RegexValidate.isStrFilter(hyAddressReq.getEmailAddress())) {
			return ResultCodes.CUST_REC_ADRESS_DETAIL_FALSE;
		}
		return ResultCodes.NORMAL;
	}

	public HyAddressReq getHyAddressReq() {
		return hyAddressReq;
	}

	public void setHyAddressReq(HyAddressReq hyAddressReq) {
		this.hyAddressReq = hyAddressReq;
	}


}
