package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.SaveInviteCodeAndNameReq;
import com.hengyuan.hicash.utils.RegexValidate;


public class SaveInviteCodeAndNameVal {
	
	private SaveInviteCodeAndNameReq userInfoReq;
	
	public SaveInviteCodeAndNameVal(SaveInviteCodeAndNameReq userInfoReq) {
		this.userInfoReq = userInfoReq;
	}
    
	public String validate(){
		if (RegexValidate.isNull(userInfoReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(userInfoReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		/* 邀请码 */
		if (RegexValidate.isNull(userInfoReq.getInviteCode())) {
			return ResultCodes.UPDATELANUSERINFO_INVITECODE_ISNULL;
		}

		if (!RegexValidate.isNumber(userInfoReq.getInviteCode())) {
			return ResultCodes.UPDATELANUSERINFO_INVITECODE_ILLEGAL;
		}
		return ResultCodes.NORMAL;
	}
}
