package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author fish
 *
 * @date 2017年1月15日 下午2:03:21
 */
public class UpdateCustCardReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String userName;
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
	private String hyIndustryCode;//行业
	
	

	public String getHyIndustryCode() {
		return hyIndustryCode;
	}

	public void setHyIndustryCode(String hyIndustryCode) {
		this.hyIndustryCode = hyIndustryCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
