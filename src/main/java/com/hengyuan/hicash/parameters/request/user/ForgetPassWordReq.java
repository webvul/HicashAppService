package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 获取资料接口
 * 忘记密码Req
 * @author Eric.shi
 * @create date 2014-07-22
 *
 */

public class ForgetPassWordReq extends RequestSequence  {

	private static final long serialVersionUID = -5503777183900209713L;
	
	/*手机号码*/
	private String mobile;
	
	/*验证码*/
	private String validateCode;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
}
