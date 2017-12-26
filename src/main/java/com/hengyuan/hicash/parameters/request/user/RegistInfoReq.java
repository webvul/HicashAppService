package com.hengyuan.hicash.parameters.request.user;

/***
 * 
 * @author laughing.peng
 * @create date 2014-07-22
 */
public class RegistInfoReq {

	/** 用户名 */
	private String userName;

	/** 真实姓名 */
	private String realName;

	/** 身份证号 */
	private String identityNo;

	/** 邮箱地址 */
	private String emailUrl;

	/** 邀请码 */
	private String invitationCode;

	/** 身份 */
	private String custType;

	/** 请求唯一序列ID */
	protected String uuid;

	/** 注册手机号 */
	private String iptPhoneRgt;
	
	/** 验证码 */
	private String validateCode;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getEmailUrl() {
		return emailUrl;
	}

	public void setEmailUrl(String emailUrl) {
		this.emailUrl = emailUrl;
	}

	public String getInvitationCode() {
		return invitationCode;
	}

	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getIptPhoneRgt() {
		return iptPhoneRgt;
	}

	public void setIptPhoneRgt(String iptPhoneRgt) {
		this.iptPhoneRgt = iptPhoneRgt;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}
	
	

}
