package com.hengyuan.hicash.service.validate.update;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UpdateRelaNameReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * @author Administrator
 *
 */
public class UpdateRelaNameVal {

	private UpdateRelaNameReq valReq;

	public UpdateRelaNameVal(UpdateRelaNameReq val){
		this.valReq = val;
	}

	public String validate() {


		/* 验证用户名 */
		if (RegexValidate.isNull(valReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}

		if (!RegexValidate.isUsername(valReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		
//		if (RegexValidate.isNull(valReq.getRealName())) {
//			return ResultCodes.REGISTER_REALNAME_ISNULL;
//		}
		
		return ResultCodes.NORMAL;
		
	
}

	public UpdateRelaNameReq getValReq() {
		return valReq;
	}

	public void setValReq(UpdateRelaNameReq valReq) {
		this.valReq = valReq;
	}
}