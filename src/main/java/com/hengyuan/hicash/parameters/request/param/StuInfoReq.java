package com.hengyuan.hicash.parameters.request.param;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/** 
 * @author dong.liu 
 * 2017-1-9 下午6:04:41
 * 类说明 :学生个人信息请求
 */
public class StuInfoReq extends RequestSequence{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String userName;//用户名
	private String realName;//真实姓名
	private String identityNo;//身份证号
	private String maritalStatus;//婚姻状况
	private String emailAdress;//电子邮箱
	/** 省 */
	private String otherAdressProvince;
	private String otherAdressCity;//市
	/** 区/县 */
	private String otherAdressArea;
	/** 其它街道地址 */
	private String otherAccommodationAddress;
	
	/** 身份证有效期开始时间 */
	private String idcard_validity_startdate;

	/** 身份证有效期结束时间 */
	private String idcard_validity_enddate;
	
	//贷款用途
	private String loan_purpose;
	
	public String getLoan_purpose() {
		return loan_purpose;
	}

	public void setLoan_purpose(String loan_purpose) {
		this.loan_purpose = loan_purpose;
	}
	
	public String getIdcard_validity_startdate() {
		return idcard_validity_startdate;
	}
	public void setIdcard_validity_startdate(String idcard_validity_startdate) {
		this.idcard_validity_startdate = idcard_validity_startdate;
	}
	public String getIdcard_validity_enddate() {
		return idcard_validity_enddate;
	}
	public void setIdcard_validity_enddate(String idcard_validity_enddate) {
		this.idcard_validity_enddate = idcard_validity_enddate;
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
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public String getOtherAdressProvince() {
		return otherAdressProvince;
	}
	public void setOtherAdressProvince(String otherAdressProvince) {
		this.otherAdressProvince = otherAdressProvince;
	}
	public String getOtherAdressCity() {
		return otherAdressCity;
	}
	public void setOtherAdressCity(String otherAdressCity) {
		this.otherAdressCity = otherAdressCity;
	}
	public String getOtherAdressArea() {
		return otherAdressArea;
	}
	public void setOtherAdressArea(String otherAdressArea) {
		this.otherAdressArea = otherAdressArea;
	}
	public String getOtherAccommodationAddress() {
		return otherAccommodationAddress;
	}
	public void setOtherAccommodationAddress(String otherAccommodationAddress) {
		this.otherAccommodationAddress = otherAccommodationAddress;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	

}
