package com.hengyuan.hicash.parameters.request.mktApp;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 
* @author dong.liu 
* 2017-5-12 下午5:46:50
* 类说明 :嗨女神正式订单请求参数
 */
public class HinsCreateCreditReq extends RequestSequence{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String app_application_no;//授权真实订单号
	private String realname;//姓名
	private String mobile;//手机号
	private String identityno;//身份证号
	private String username;//用户名
	private String sx_amount;//授信额度
	private String maritalStatus;//婚姻状况
	private String educational;//最高学历
	private String nowProv;//居住地址(省)
	private String nowCity;//居住地址(市)
	private String nowArea;//居住地址(区)
	private String nowAddress;//现居详细地址
	private String emailAdress;//电子邮箱
	private String unitName;//单位名称
	private String unitTelArea;//单位电话（区号）
	private String unitTel;//单位电话（电话）
	private String unitWorkYear;//工作年限
	private String unitProv;//单位地址(省)
	private String unitCity;//单位地址(市)
	private String unitArea;//单位地址(区)
	private String unitAddress;//单位详细地址
	private String zXRealname;//直系亲属姓名
	private String zXRelation;//关系
	private String zXMobile;//联系方式（电话）
	private String jJRealname;//紧急联系人姓名
	private String jJRelation;//关系
	private String jJMobile;//联系方式（电话）
	private String credit_type;//类型（授信，提额）
	private String status;//审核状态（新申请，等待司机报告，等待运营商报告，待系统审核，待人工审核，审核通过，取消,审核拒绝）
	private String node;//审核节点（审核中，审核完成,取消）
	private String sHName;//审核操作人
	private String sHStartTime;//审核开始时间
	private String sHEndTime;//审核结束时间
	private String sHDesc;//审核描述
	private String create_date;//创建时间
	private String create_user;//创建人
	private String temp_no;//临时订单号
	
	public String getTemp_no() {
		return temp_no;
	}
	public void setTemp_no(String temp_no) {
		this.temp_no = temp_no;
	}
	public String getApp_application_no() {
		return app_application_no;
	}
	public void setApp_application_no(String app_application_no) {
		this.app_application_no = app_application_no;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getIdentityno() {
		return identityno;
	}
	public void setIdentityno(String identityno) {
		this.identityno = identityno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSx_amount() {
		return sx_amount;
	}
	public void setSx_amount(String sx_amount) {
		this.sx_amount = sx_amount;
	}
	public String getMaritalStatus() {
		return maritalStatus;
	}
	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}
	public String getEducational() {
		return educational;
	}
	public void setEducational(String educational) {
		this.educational = educational;
	}
	public String getNowProv() {
		return nowProv;
	}
	public void setNowProv(String nowProv) {
		this.nowProv = nowProv;
	}
	public String getNowCity() {
		return nowCity;
	}
	public void setNowCity(String nowCity) {
		this.nowCity = nowCity;
	}
	public String getNowArea() {
		return nowArea;
	}
	public void setNowArea(String nowArea) {
		this.nowArea = nowArea;
	}
	public String getNowAddress() {
		return nowAddress;
	}
	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress;
	}
	public String getEmailAdress() {
		return emailAdress;
	}
	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public String getUnitTelArea() {
		return unitTelArea;
	}
	public void setUnitTelArea(String unitTelArea) {
		this.unitTelArea = unitTelArea;
	}
	public String getUnitTel() {
		return unitTel;
	}
	public void setUnitTel(String unitTel) {
		this.unitTel = unitTel;
	}
	public String getUnitWorkYear() {
		return unitWorkYear;
	}
	public void setUnitWorkYear(String unitWorkYear) {
		this.unitWorkYear = unitWorkYear;
	}
	public String getUnitProv() {
		return unitProv;
	}
	public void setUnitProv(String unitProv) {
		this.unitProv = unitProv;
	}
	public String getUnitCity() {
		return unitCity;
	}
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}
	public String getUnitArea() {
		return unitArea;
	}
	public void setUnitArea(String unitArea) {
		this.unitArea = unitArea;
	}
	public String getUnitAddress() {
		return unitAddress;
	}
	public void setUnitAddress(String unitAddress) {
		this.unitAddress = unitAddress;
	}
	public String getzXRealname() {
		return zXRealname;
	}
	public void setzXRealname(String zXRealname) {
		this.zXRealname = zXRealname;
	}
	public String getzXRelation() {
		return zXRelation;
	}
	public void setzXRelation(String zXRelation) {
		this.zXRelation = zXRelation;
	}
	public String getzXMobile() {
		return zXMobile;
	}
	public void setzXMobile(String zXMobile) {
		this.zXMobile = zXMobile;
	}
	public String getjJRealname() {
		return jJRealname;
	}
	public void setjJRealname(String jJRealname) {
		this.jJRealname = jJRealname;
	}
	public String getjJRelation() {
		return jJRelation;
	}
	public void setjJRelation(String jJRelation) {
		this.jJRelation = jJRelation;
	}
	public String getjJMobile() {
		return jJMobile;
	}
	public void setjJMobile(String jJMobile) {
		this.jJMobile = jJMobile;
	}
	
	public String getCredit_type() {
		return credit_type;
	}
	public void setCredit_type(String credit_type) {
		this.credit_type = credit_type;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNode() {
		return node;
	}
	public void setNode(String node) {
		this.node = node;
	}
	public String getsHName() {
		return sHName;
	}
	public void setsHName(String sHName) {
		this.sHName = sHName;
	}
	public String getsHStartTime() {
		return sHStartTime;
	}
	public void setsHStartTime(String sHStartTime) {
		this.sHStartTime = sHStartTime;
	}
	public String getsHEndTime() {
		return sHEndTime;
	}
	public void setsHEndTime(String sHEndTime) {
		this.sHEndTime = sHEndTime;
	}
	public String getsHDesc() {
		return sHDesc;
	}
	public void setsHDesc(String sHDesc) {
		this.sHDesc = sHDesc;
	}
	public String getCreate_date() {
		return create_date;
	}
	public void setCreate_date(String create_date) {
		this.create_date = create_date;
	}
	public String getCreate_user() {
		return create_user;
	}
	public void setCreate_user(String create_user) {
		this.create_user = create_user;
	}
	
	
	
	
	

}
