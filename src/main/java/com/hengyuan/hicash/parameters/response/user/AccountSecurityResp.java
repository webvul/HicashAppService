package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 查询对应信息是否已经绑定或者是否已经验证
 * @author Eric.shi
 * @create date 2014-08-19
 *
 */
public class AccountSecurityResp extends ParmResponse  {

	/** 返回代码 */
	private String resultCode;
	
	/** 手机返回代码 0.未绑定 1.已绑定 2.已验证 */
	private Integer mobileCode;
	
	/** 邮箱返回代码 0.未绑定 1.已绑定 2.已验证 */
	private Integer emailCode;
	
	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public Integer getMobileCode() {
		return mobileCode;
	}

	public void setMobileCode(Integer mobileCode) {
		this.mobileCode = mobileCode;
	}

	public Integer getEmailCode() {
		return emailCode;
	}

	public void setEmailCode(Integer emailCode) {
		this.emailCode = emailCode;
	}

}
