package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 商户入驻-新增商户
 * 
 * @author Cary.Liu
 * @createDate 2015-07-10
 * 
 */
public class AddSupplierInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 手机号码 */
	private String mobileNo;

	/** 验证码 */
	private String identifyCode;

	/** 用户名称 */
	private String supplierUserName;

	/** 用户密码 */
	private String supplierUserPassword;

	/** 姓名 */
	private String realName;

	/** 商户名称 */
	private String supplierName;

	/** 商户联系人 */
	// private String supContactName;

	/** 商户联系人电话 */
	// private String supplierTel;

	/** 所属行业 */
	private String supIndustry;

	/** 所属省份 */
	private String province;

	/** 所属城市 */
	private String city;

	/** 营业执照号码 */
	private String supBusiLice;

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSupplierUserName() {
		return supplierUserName;
	}

	public void setSupplierUserName(String supplierUserName) {
		this.supplierUserName = supplierUserName;
	}

	public String getSupIndustry() {
		return supIndustry;
	}

	public void setSupIndustry(String supIndustry) {
		this.supIndustry = supIndustry;
	}

	public String getSupplierUserPassword() {
		return supplierUserPassword;
	}

	public void setSupplierUserPassword(String supplierUserPassword) {
		this.supplierUserPassword = supplierUserPassword;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getSupBusiLice() {
		return supBusiLice;
	}

	public void setSupBusiLice(String supBusiLice) {
		this.supBusiLice = supBusiLice;
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

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

}
