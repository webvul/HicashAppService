package com.hengyuan.hicash.service.validate.query;

import com.hengyuan.hicash.constant.ResultCodes;
import com.hengyuan.hicash.parameters.request.user.LoginReq;
import com.hengyuan.hicash.utils.RegexValidate;

public class LoginVal {
	
	private LoginReq loginReq;
	
	public LoginVal(LoginReq loginReq){
		this.loginReq = loginReq;
	}

	public String validate() {
		
		if (RegexValidate.isNull(loginReq.getUuid())) {
			return ResultCodes.UUIDNULL;
		}
		
		if (!RegexValidate.isUuid(loginReq.getUuid())) {
			return ResultCodes.UUIDILLEGAL;
		}
		
		//验证城市
		if (RegexValidate.isNull(loginReq.getCityCode())) {
			return ResultCodes.LOGIN_CITYCODE_IS_NULL;
		}

		if (!RegexValidate.isSelectToAddress(loginReq.getCityCode())) {
			return ResultCodes.LOGIN_CITYCODE_ILLEGAL;
		}
		
		//验证用户名
		if (RegexValidate.isNull(loginReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_IS_NULL;
		}
		
		if (!RegexValidate.isUsername(loginReq.getUserName())) {
			return ResultCodes.LOGIN_USERNAME_ILLEGAL;
		}
		
		//验证密码
		if (RegexValidate.isNull(loginReq.getPassWord())) {
			return ResultCodes.LOGIN_PASSWORD_IS_NULL;
		}
		
		if(RegexValidate.isChinese(loginReq.getPassWord())){
			return ResultCodes.LOGIN_PASSWORD_ILLEGAL;
		}
		
		return ResultCodes.NORMAL;
		
	}
	
	public LoginReq getLoginReq() {
		return loginReq;
	}

	public void setLoginReq(LoginReq loginReq) {
		this.loginReq = loginReq;
	}
	

}
