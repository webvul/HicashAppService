package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 用户注册的请求参数
 * 
 * @author Cary.Liu
 * @createDate 2015-04-21
 * 
 */
public class RegisterReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 客户类型 */
	private String custType;

	/** 居住的城市 */
	private String liveCity;

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

	/** 注册来源 */
	private String registerFrom;

	/** 客户来源于那个平台（第三方平台） */
	private String custFrom;

	/** 活动来源 */
	private String activeFrom;

	public String getActiveFrom() {
		return activeFrom;
	}

	public void setActiveFrom(String activeFrom) {
		this.activeFrom = activeFrom;
	}

	public String getCustFrom() {
		return custFrom;
	}

	public void setCustFrom(String custFrom) {
		this.custFrom = custFrom;
	}

	public String getRegisterFrom() {
		return registerFrom;
	}

	public void setRegisterFrom(String registerFrom) {
		this.registerFrom = registerFrom;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getLiveCity() {
		return liveCity;
	}

	public void setLiveCity(String liveCity) {
		this.liveCity = liveCity;
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

}
