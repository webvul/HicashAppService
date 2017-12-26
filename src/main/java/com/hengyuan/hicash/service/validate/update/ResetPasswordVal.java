package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.ResetPasswordReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class ResetPasswordVal {

	private ResetPasswordReq resetPasswordReq;
	
	public ResetPasswordVal(ResetPasswordReq resetPasswordReq){
		
		this.resetPasswordReq = resetPasswordReq;
		
	}

	public String validate() {
		
		if (RegexValidate.isNull(resetPasswordReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(resetPasswordReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		if (RegexValidate.isNull(resetPasswordReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(resetPasswordReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		if (RegexValidate.isNull(resetPasswordReq.getNewPassword())) {
			return ResultCodes.RESET_NEWPSSWORD_NOTNULL;
		}
		
		if (!RegexValidate.isPassword(resetPasswordReq.getNewPassword())) {
			return ResultCodes.RESET_NEWPSSWORD_FALSE;
		}
		
//		if (RegexValidate.isNull(resetPasswordReq.getResetPassword())) {
//			return ResultCodes.RESET_RESTPASSWORD_ISNULL;
//		}
//		
//		if (!RegexValidate.isPassword(resetPasswordReq.getResetPassword())) {
//			return ResultCodes.RESET_RESTPASSWORD_FALSE;
//		}
//		
//		if (!RegexValidate.isCon(resetPasswordReq.getNewPassword(),resetPasswordReq.getResetPassword())) {
//			return ResultCodes.RESET_PASSWORD_NOT_EQ;
//		}
		
		return ResultCodes.NORMAL;
	}
	
	public ResetPasswordReq getResetPasswordReq() {
		return resetPasswordReq;
	}

	public void setResetPasswordReq(ResetPasswordReq resetPasswordReq) {
		this.resetPasswordReq = resetPasswordReq;
	}
}
