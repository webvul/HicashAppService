package com.hengyuan.hicash.service.validate.update;

import java.util.regex.Pattern;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.BlackCertNoUpdateReq;
import com.hengyuan.hicash.parameters.request.user.CallPhoneDataNumUpdateReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author Administrator
 *
 */
public class BlackCertNoUpdateVal {

	private BlackCertNoUpdateReq valReq;

	public BlackCertNoUpdateVal(BlackCertNoUpdateReq val){
		this.valReq = val;
	}

	public String validate() {

//		/* 验证uuid */
//		if (RegexValidate.isNull(valReq.getUuid())) {
//			return ResultCodes.UUIDNULL;
//		}
//
//		if (!RegexValidate.isUuid(valReq.getUuid())) {
//			return ResultCodes.UUIDILLEGAL;
//		}

		/* 验证身份证号码 */
		if (RegexValidate.isNull(valReq.getCertNo())) {
			return ResultCodes.BLACK_CERTNO_UPDATE_NOTNULL;
		}

		
		
		return ResultCodes.NORMAL;
	}

	public BlackCertNoUpdateReq getValReq() {
		return valReq;
	}

	public void setValReq(BlackCertNoUpdateReq valReq) {
		this.valReq = valReq;
	}


}
