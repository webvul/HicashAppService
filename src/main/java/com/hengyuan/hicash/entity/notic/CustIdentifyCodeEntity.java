package com.hengyuan.hicash.entity.notic;

public class CustIdentifyCodeEntity {

	/** 客户用户名 */
	private String userName;

	/** 手机号 */
	private String mobileNo;

	/** 验证码 */
	private String identifyCode;

	/** 验证码有效时间 */
	private String validateTime;

	/** 发送验证时间 */
	private String createTime;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIdentifyCode() {
		return identifyCode;
	}

	public void setIdentifyCode(String identifyCode) {
		this.identifyCode = identifyCode;
	}

	public String getValidateTime() {
		return validateTime;
	}

	public void setValidateTime(String validateTime) {
		this.validateTime = validateTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
