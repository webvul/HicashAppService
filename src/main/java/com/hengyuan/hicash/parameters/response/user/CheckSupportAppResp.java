package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

public class CheckSupportAppResp extends ParmResponse {

	private String mobileHome;
	private String success;
	private String isCredit;//是否授信 0:未授信,1:授信
	private String isJump;//是否要跳转授信页面
	private String isCash;//是否可以提现
	private String idcard_isexpired;//身份证是否过期
	private String strButton;//按钮
	private String strButton_url;//按钮url
	private String but_msg_1;//返回提示消息
	private String isTiE;//能否提额，Y提额N不提额（嗨女神用）
	
	private String idcard_ZL168url;//个人头像照
	
	private String idcard_ZL02url;//身份证正面
	
	private String idcard_ZL03url;//身份证反面
	
	private String permanentAddressProvince;
	private String permanentAddressCity;
	private String permanentAddressArea;
	private String permanentAddressRaod;
	private String name;
	private String nation;
	private String idCardValStartDate;
	private String idCardValEndDate;
	private String identityNo;
	private String idcardFrom;
	
	
	
	public String getIsTiE() {
		return isTiE;
	}

	public void setIsTiE(String isTiE) {
		this.isTiE = isTiE;
	}

	public String getBut_msg_1() {
		return but_msg_1;
	}

	public void setBut_msg_1(String but_msg_1) {
		this.but_msg_1 = but_msg_1;
	}

	public String getStrButton_url() {
		return strButton_url;
	}

	public void setStrButton_url(String strButton_url) {
		this.strButton_url = strButton_url;
	}
	
	
	
	public String getStrButton() {
		return strButton;
	}

	public void setStrButton(String strButton) {
		this.strButton = strButton;
	}

	public String getIsJump() {
		return isJump;
	}

	public void setIsJump(String isJump) {
		this.isJump = isJump;
	}

	public String getIsCash() {
		return isCash;
	}

	public void setIsCash(String isCash) {
		this.isCash = isCash;
	}

	public String getMobileHome() {
		return mobileHome;
	}

	public void setMobileHome(String mobileHome) {
		this.mobileHome = mobileHome;
	}

	public String getSuccess() {
		return success;
	}

	public void setSuccess(String success) {
		this.success = success;
	}
	
	public String getIsCredit() {
		return isCredit;
	}

	public void setIsCredit(String isCredit) {
		this.isCredit = isCredit;
	}

	public String getIdcard_isexpired() {
		return idcard_isexpired;
	}

	public void setIdcard_isexpired(String idcard_isexpired) {
		this.idcard_isexpired = idcard_isexpired;
	}

	public String getIdcard_ZL168url() {
		return idcard_ZL168url;
	}

	public void setIdcard_ZL168url(String idcard_ZL168url) {
		this.idcard_ZL168url = idcard_ZL168url;
	}

	public String getIdcard_ZL02url() {
		return idcard_ZL02url;
	}

	public void setIdcard_ZL02url(String idcard_ZL02url) {
		this.idcard_ZL02url = idcard_ZL02url;
	}

	public String getIdcard_ZL03url() {
		return idcard_ZL03url;
	}

	public void setIdcard_ZL03url(String idcard_ZL03url) {
		this.idcard_ZL03url = idcard_ZL03url;
	}

	public String getPermanentAddressProvince() {
		return permanentAddressProvince;
	}

	public void setPermanentAddressProvince(String permanentAddressProvince) {
		this.permanentAddressProvince = permanentAddressProvince;
	}

	public String getPermanentAddressCity() {
		return permanentAddressCity;
	}

	public void setPermanentAddressCity(String permanentAddressCity) {
		this.permanentAddressCity = permanentAddressCity;
	}

	public String getPermanentAddressArea() {
		return permanentAddressArea;
	}

	public void setPermanentAddressArea(String permanentAddressArea) {
		this.permanentAddressArea = permanentAddressArea;
	}

	public String getPermanentAddressRaod() {
		return permanentAddressRaod;
	}

	public void setPermanentAddressRaod(String permanentAddressRaod) {
		this.permanentAddressRaod = permanentAddressRaod;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}

	public String getIdCardValStartDate() {
		return idCardValStartDate;
	}

	public void setIdCardValStartDate(String idCardValStartDate) {
		this.idCardValStartDate = idCardValStartDate;
	}

	public String getIdCardValEndDate() {
		return idCardValEndDate;
	}

	public void setIdCardValEndDate(String idCardValEndDate) {
		this.idCardValEndDate = idCardValEndDate;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getIdcardFrom() {
		return idcardFrom;
	}

	public void setIdcardFrom(String idcardFrom) {
		this.idcardFrom = idcardFrom;
	}
	
	

}
