package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ChangePasswordReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class ChangePasswordVal {

	private ChangePasswordReq changePasswordReq;

	public ChangePasswordVal(ChangePasswordReq changePasswordReq) {
		this.changePasswordReq = changePasswordReq;
	}

	public String validate() {
		
		if (RegexValidate.isNull(changePasswordReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}

		if (!RegexValidate.isUuid(changePasswordReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}

		// 验证用户名
		if (RegexValidate.isNull(changePasswordReq.getUsername())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(changePasswordReq.getUsername())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}

		// 老密码
		if (RegexValidate.isNull(changePasswordReq.getOldPassword())) {
			return ResultCodes.CHANGEPSW_OLDPASSWORD_ISNULL;
		}
		
		// 新密码
		if (RegexValidate.isNull(changePasswordReq.getNewPassword())) {
			return ResultCodes.CHANGEPSW_NEWPSW_ISNULLNULL;
		}

		if (RegexValidate.isChinese(changePasswordReq.getNewPassword())){
			return ResultCodes.CHANGEPSW_NEWPSW_ILLEGAL;
		}
		if(!RegexValidate.isNewPassword(changePasswordReq.getNewPassword())){
			return ResultCodes.CHANGEPSW_NEWPSW_ILLEGAL;
		}
		
		
		return ResultCodes.NORMAL;
	}

	public ChangePasswordReq getChangePasswordReq() {
		return changePasswordReq;
	}

	public void setChangePasswordReq(ChangePasswordReq changePasswordReq) {
		this.changePasswordReq = changePasswordReq;
	}

}
