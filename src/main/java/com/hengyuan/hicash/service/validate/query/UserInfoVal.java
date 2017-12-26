package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.UserInfoReq;
import com.hengyuan.hicash.utils.RegexValidate;

/**
 * 是否为蓝领用户 val
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
public class UserInfoVal {

	private UserInfoReq userInfoReq;
	
	public UserInfoVal(UserInfoReq userInfoReq){
		this.userInfoReq = userInfoReq;
	}
	
	public String validate(){
		
		if (RegexValidate.isNull(userInfoReq.getUserName())) {
			return ResultCodes.USER_ERROR_ISNULL;
		}
		
		if (!RegexValidate.isUsername(userInfoReq.getUserName())) {
			return ResultCodes.USER_NAME_ERROR_CANTCHAR;
		}
		
		return ResultCodes.NORMAL;
	}
			
	
}
