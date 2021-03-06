package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
 * @author fish
 *
 * @date 2017年1月9日 下午4:52:58
 */
public class CollarPersonReq extends RequestSequence {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	private String real_name;

	private String id_no;

	private String marital_status;

	private String education_code;

	private String province;

	private String city;

	private String area;

	private String address;

	private String email;

	private String unit_name;

	private String unit_phone_area;

	private String unit_phone;

	private String work_year;

	private String unit_province;

	private String unit_city;

	private String unit_area;

	private String unit_address;
	
	/**qq号 是通过邮箱截取的。*/
	private String qqNumber;
	/**单位行业*/
	private  String unit_properties;

//	private String credit_card;

	private String loan_purpose;
	
	
	
	public String getLoan_purpose() {
		return loan_purpose;
	}

	public void setLoan_purpose(String loan_purpose) {
		this.loan_purpose = loan_purpose;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	/** 身份证有效期开始时间 */
	private String idcard_validity_startdate;

	/** 身份证有效期结束时间 */
	private String idcard_validity_enddate;
	
	/**是否为全职司机  0其他 1兼职 2全职*/
	private String fulltimeDriver;
	

	public String getFulltimeDriver() {
		return fulltimeDriver;
	}

	public void setFulltimeDriver(String fulltimeDriver) {
		this.fulltimeDriver = fulltimeDriver;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getId_no() {
		return id_no;
	}

	public void setId_no(String id_no) {
		this.id_no = id_no;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getEducation_code() {
		return education_code;
	}

	public void setEducation_code(String education_code) {
		this.education_code = education_code;
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

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUnit_name() {
		return unit_name;
	}

	public void setUnit_name(String unit_name) {
		this.unit_name = unit_name;
	}

	public String getUnit_phone() {
		return unit_phone;
	}

	public void setUnit_phone(String unit_phone) {
		this.unit_phone = unit_phone;
	}

	public String getWork_year() {
		return work_year;
	}

	public void setWork_year(String work_year) {
		this.work_year = work_year;
	}

	public String getUnit_province() {
		return unit_province;
	}

	public void setUnit_province(String unit_province) {
		this.unit_province = unit_province;
	}

	public String getUnit_city() {
		return unit_city;
	}

	public void setUnit_city(String unit_city) {
		this.unit_city = unit_city;
	}

	public String getUnit_area() {
		return unit_area;
	}

	public void setUnit_area(String unit_area) {
		this.unit_area = unit_area;
	}

	public String getUnit_address() {
		return unit_address;
	}

	public void setUnit_address(String unit_address) {
		this.unit_address = unit_address;
	}

//	public String getCredit_card() {
//		return credit_card;
//	}
//
//	public void setCredit_card(String credit_card) {
//		this.credit_card = credit_card;
//	}

	public String getUnit_phone_area() {
		return unit_phone_area;
	}

	public void setUnit_phone_area(String unit_phone_area) {
		this.unit_phone_area = unit_phone_area;
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

	public String getUnit_properties() {
		return unit_properties;
	}

	public void setUnit_properties(String unit_properties) {
		this.unit_properties = unit_properties;
	}

	

}
