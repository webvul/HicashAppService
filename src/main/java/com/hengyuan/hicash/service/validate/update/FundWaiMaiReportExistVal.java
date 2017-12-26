package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.FundWaiMaiReportExistReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class FundWaiMaiReportExistVal {
	private FundWaiMaiReportExistReq req;

	public FundWaiMaiReportExistVal(FundWaiMaiReportExistReq req) {
		this.req = req;
	}

	public String validate(){
		
		if(req.getType().equals("GJJ")){

			
		/* 真实姓名 */
		if (RegexValidate.isNull(req.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ISNULL;
		}

		if (!RegexValidate.isRealName(req.getRealName())) {
			return ResultCodes.REGISTER_REALNAME_ILLEGAL;
		}

		/* 身份证号码 */
		if (RegexValidate.isNull(req.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ISNULL;
		}

		if (!RegexValidate.isIdCard(req.getIdentityNo())) {
			return ResultCodes.REGISTER_IDCARD_ILLEGAL;
		}
		
		}else{
			
		if(RegexValidate.isNull(req.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_ISNULL;
		}
		
		if(!RegexValidate.isIphon(req.getMobileNo())){
			return ResultCodes.SENDAMOUNTCODE_MOBILE_FAIL;
		}
		
		}
		if(RegexValidate.isNull(req.getType())){
			return ResultCodes.CHECK_GJJ_WM_NULL;
		}
		return ResultCodes.NORMAL;
	}

	public FundWaiMaiReportExistReq getReq() {
		return req;
	}

	public void setReq(FundWaiMaiReportExistReq req) {
		this.req = req;
	}

	
	
}
