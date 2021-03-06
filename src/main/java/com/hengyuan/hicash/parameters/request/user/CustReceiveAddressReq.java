package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;
/**
 * 收货地址
 * @author ding
 *
 */
public class CustReceiveAddressReq extends RequestSequence{
	private String id;
	//用户名
	private String cust_user;
	//真实姓名
	private String real_name;
	//手机号码
	private String mobile;
	//省
	private String province_code;
	//市
	private String city_code;
	//区
	private String area_code;
	//详细地址
	private String detail_address;
	//是否默认
	private String is_default;
	
	//备注1单位地址2家庭地址
	private String remark;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCust_user() {
		return cust_user;
	}
	public void setCust_user(String cust_user) {
		this.cust_user = cust_user;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getCity_code() {
		return city_code;
	}
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	public String getArea_code() {
		return area_code;
	}
	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
	public String getDetail_address() {
		return detail_address;
	}
	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}
	public String getIs_default() {
		return is_default;
	}
	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
}
