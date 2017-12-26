package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 蓝领活动注册req
 * 
 * @author Cary.Liu
 * @createDate 2016-01-11
 *
 */
public class RegisterByllReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 客户类型 */
	private String custType;

	/** 手机号码 */
	private String mobileNo;

	/** 验证码 */
	private String identifyingCode;

	/** 密码 */
	private String passWord;

	/** 真实姓名 */
	private String realName;

	/** 身份证号码 */
	private String idCard;

	/** 邀请码 */
	private String inviteCode;

	/** 门店号 */
	private String storeCode;

	/** 用户现场照大图url */
	private String userScenepicUrl;

	/** 用户现场照小图url */
	private String userScenepicThumUrl;

	/** 注册来源 */
	private String registerFrom;

	/** 客户来源于那个平台（第三方平台） */
	private String custFrom;

	public String getUserScenepicUrl() {
		return userScenepicUrl;
	}

	public void setUserScenepicUrl(String userScenepicUrl) {
		this.userScenepicUrl = userScenepicUrl;
	}

	public String getUserScenepicThumUrl() {
		return userScenepicThumUrl;
	}

	public void setUserScenepicThumUrl(String userScenepicThumUrl) {
		this.userScenepicThumUrl = userScenepicThumUrl;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getIdentifyingCode() {
		return identifyingCode;
	}

	public void setIdentifyingCode(String identifyingCode) {
		this.identifyingCode = identifyingCode;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}

	public String getCustFrom() {
		return custFrom;
	}

	public void setCustFrom(String custFrom) {
		this.custFrom = custFrom;
	}

}
