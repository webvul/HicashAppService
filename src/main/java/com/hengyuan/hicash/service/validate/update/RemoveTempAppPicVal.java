package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.upload.RemoveTempAppPicReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class RemoveTempAppPicVal {

	private RemoveTempAppPicReq picReq;

	public RemoveTempAppPicVal(RemoveTempAppPicReq picReq) {
		this.picReq = picReq;
	}
	
	public String validate(){
		
		// 验证用户名
		if (RegexValidate.isNull(picReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}

		if (!RegexValidate.isUsername(picReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
				
		if(RegexValidate.isNull(picReq.getPicId())){;
			return ResultCodes.REMOVEAPPPIC_PICID_ISNULL;
		}
		
		if(!RegexValidate.isNumber(picReq.getPicId())){
			return ResultCodes.REMOVEAPPPIC_PICID_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
	}

	public RemoveTempAppPicReq getPicReq() {
		return picReq;
	}

	public void setPicReq(RemoveTempAppPicReq picReq) {
		this.picReq = picReq;
	}

}
