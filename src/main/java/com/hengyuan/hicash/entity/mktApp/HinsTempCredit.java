package com.hengyuan.hicash.entity.mktApp;

/**
 * 
* @author dong.liu 
* 2017-5-12 下午4:50:02
* 类说明:嗨女神临时申请件表
 */
public class HinsTempCredit {
	
	private String id;//主键id
	private String temp_application_no;//临时授权单号(无实际意义)
	private String hy_industry_code;//行业代码
	private String username;//客户用户名
	private String createapp_flag;//是否已创建真实授权单 Y：是
	private String validate_type;//申请件流程状态
	private String validate_typea;//申请件流程状态(最高流程)
	private String app_application_no;//真实审核定单号
	private String credit_type;//授信类型 SX：授信 TE:提额
	private String hinsPid;	//嗨女神商城商品的ID
	private String create_date;//创建时间
	private String update_date;//更新时间
	
	
	public String getHinsPid() {
		return hinsPid;
	}
	public void setHinsPid(String hinsPid) {
		this.hinsPid = hinsPid;
	}
	public String getHy_industry_code() {
		return hy_industry_code;
	}
	public void setHy_industry_code(String hy_industry_code) {
		this.hy_industry_code = hy_industry_code;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTemp_application_no() {
		return temp_application_no;
	}
	public void setTemp_application_no(String temp_application_no) {
		this.temp_application_no = temp_application_no;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getCreateapp_flag() {
		return createapp_flag;
	}
	public void setCreateapp_flag(String createapp_flag) {
		this.createapp_flag = createapp_flag;
	}
	public String getValidate_type() {
		return validate_type;
	}
	public void setValidate_type(String validate_type) {
		this.validate_type = validate_type;
	}
	public String getValidate_typea() {
		return validate_typea;
	}
	public void setValidate_typea(String validate_typea) {
		this.validate_typea = validate_typea;
	}
	public String getApp_application_no() {
		return app_application_no;
	}
	public void setApp_application_no(String app_application_no) {
		this.app_application_no = app_application_no;
	}
	
	public String getCredit_type() {
		return credit_type;
	}
	public void setCredit_type(String credit_type) {
		this.credit_type = credit_type;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getUpdate_date() {
		return update_date;
	}
	public void setUpdate_date(String update_date) {
		this.update_date = update_date;
	}

}
