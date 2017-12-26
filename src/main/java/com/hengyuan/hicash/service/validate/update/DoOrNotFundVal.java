package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.DoOrNotFundReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class DoOrNotFundVal {
	private DoOrNotFundReq req;

	public DoOrNotFundVal(DoOrNotFundReq req) {
		this.req = req;
	}

	public String validate(){
		
		/* 真实姓名 */
		if (RegexValidate.isNull(req.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

//		if (!RegexValidate.isRealName(req.getRealName())) {
//			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
//		}

		/* 身份证号码 */
		if (RegexValidate.isNull(req.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(req.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
		
		if(RegexValidate.isNull(req.getIsOrNot())){
			return ResultCodes.CHECK_GJJ_ISNULL;
		}
		return ResultCodes.NORMAL;
	}

	public DoOrNotFundReq getReq() {
		return req;
	}

	public void setReq(DoOrNotFundReq req) {
		this.req = req;
	}
	
}
