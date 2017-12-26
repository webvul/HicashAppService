package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.CheckPersonFaceReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class CheckPersonFaceVal extends RegexValidate {
	
	private CheckPersonFaceReq checkPersonFaceReq;
	
	public CheckPersonFaceVal(CheckPersonFaceReq checkPersonFaceReq){
		
		this.checkPersonFaceReq = checkPersonFaceReq;
		
	}

	public String validate(){
		
		/* 验证用户名 */
//		if (RegexValidate.isNull(checkPersonFaceReq.getIdNo())) {
//			return ResultCodes.BLACK_CERTNO_UPDATE_NOTNULL;
//		}

//		if (!RegexValidate.isUsername(checkPersonFaceReq.getUserName())) {
//			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
//		}
//		if(!RegexValidate.isAppNo(checkPersonFaceReq.getTempNo())){
//			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ILLEGAL;
//		}
//		if (RegexValidate.isNull(checkPersonFaceReq.getTempNo())) {
//			return ResultCodes.CREATEAPPPAY_TEMPAPPNO_ISNULL;
//		}
		
		return ResultCodes.NORMAL;
	}

	public CheckPersonFaceReq getCheckPersonFaceReq() {
		return checkPersonFaceReq;
	}

	public void setCheckPersonFaceReq(CheckPersonFaceReq checkPersonFaceReq) {
		this.checkPersonFaceReq = checkPersonFaceReq;
	}

	
	
	

}
