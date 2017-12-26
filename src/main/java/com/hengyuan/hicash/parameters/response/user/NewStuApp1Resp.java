package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请1返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp1Resp extends ParmResponse {

	/** 返回代码 */
	// private String resultCode;
	/** 客户类型 */
	private String custType;
	/** 客户类型名称 */
	private String custTypeName;
	
	private String realName;
	
	private String identiyNo;
	

	/** 民族 */
	private String nation;

	/** 身份证有效期开始时间 */
	private String idCardValStartDate;

	/** 身份证有效期结束时间 */
	private String idCardValEndDate;

	/** 份证有效期(期限) */
	private String idCardValidity;

	// /** 学校地址--市 */
	// private String schoolCity;
	//
	// private String schoolCityName;

	private String qqNumber;
	private String homePhoneNum;
	private String homePhoneArea;
	
	/** 借款用途 */
	private String loanUse;
	
	/** 家庭地址--省 */
	private String fimilyPro;

	private String fimilyProName;
	
	/** 家庭地址--市 */
	private String fimilyCity;

	private String fimilyCityName;

	/** 家庭地址--区 */
	private String fimilyArea;

	private String fimilyAreaName;

	/** 家庭街道地址 */
	private String fimilyRoad;
	
	
	/** 学校地址--省 */
	private String schoolPro;

	private String schoolProName;

	/** 学校地址--市 */
	private String schoolCity;

	private String schoolCityName;

	/** 学校地址--区 */
	private String schoolArea;

	private String schoolAreaName;

	/** 学校街道地址 */
	private String schoolRoad;

	/** 邮寄地址类型 */
	private String expressType;
	/** 邮寄地址名称 */
	private String expressTypeName;
	/** 合同快递地址-省 */
	private String expressProvince;

	/** 合同快递地址-市 */
	private String expressCity;

	/** 合同快递地址-区 */
	private String expressDistrict;

	/** 合同快递地址-详细 */
	private String expressDetails;
	
	private String expressProvinceName;

	/** 合同快递地址-市 */
	private String expressCityName;

	/** 合同快递地址-区 */
	private String expressDistrictName;

	/** 合同快递地址-详细 */
	private String expressDetailsName;

	public String getLoanUse() {
		return loanUse;
	}

	public void setLoanUse(String loanUse) {
		this.loanUse = loanUse;
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

	public String getIdCardValidity() {
		return idCardValidity;
	}

	public void setIdCardValidity(String idCardValidity) {
		this.idCardValidity = idCardValidity;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustTypeName() {
		return custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getIdentiyNo() {
		return identiyNo;
	}

	public void setIdentiyNo(String identiyNo) {
		this.identiyNo = identiyNo;
	}

	public String getQqNumber() {
		return qqNumber;
	}

	public void setQqNumber(String qqNumber) {
		this.qqNumber = qqNumber;
	}

	public String getHomePhoneNum() {
		return homePhoneNum;
	}

	public void setHomePhoneNum(String homePhoneNum) {
		this.homePhoneNum = homePhoneNum;
	}

	public String getHomePhoneArea() {
		return homePhoneArea;
	}

	public void setHomePhoneArea(String homePhoneArea) {
		this.homePhoneArea = homePhoneArea;
	}

	public String getFimilyPro() {
		return fimilyPro;
	}

	public void setFimilyPro(String fimilyPro) {
		this.fimilyPro = fimilyPro;
	}

	public String getFimilyProName() {
		return fimilyProName;
	}

	public void setFimilyProName(String fimilyProName) {
		this.fimilyProName = fimilyProName;
	}

	public String getFimilyCity() {
		return fimilyCity;
	}

	public void setFimilyCity(String fimilyCity) {
		this.fimilyCity = fimilyCity;
	}

	public String getFimilyCityName() {
		return fimilyCityName;
	}

	public void setFimilyCityName(String fimilyCityName) {
		this.fimilyCityName = fimilyCityName;
	}

	public String getFimilyArea() {
		return fimilyArea;
	}

	public void setFimilyArea(String fimilyArea) {
		this.fimilyArea = fimilyArea;
	}

	public String getFimilyAreaName() {
		return fimilyAreaName;
	}

	public void setFimilyAreaName(String fimilyAreaName) {
		this.fimilyAreaName = fimilyAreaName;
	}

	public String getFimilyRoad() {
		return fimilyRoad;
	}

	public void setFimilyRoad(String fimilyRoad) {
		this.fimilyRoad = fimilyRoad;
	}

	public String getSchoolPro() {
		return schoolPro;
	}

	public void setSchoolPro(String schoolPro) {
		this.schoolPro = schoolPro;
	}

	public String getSchoolProName() {
		return schoolProName;
	}

	public void setSchoolProName(String schoolProName) {
		this.schoolProName = schoolProName;
	}

	public String getSchoolCity() {
		return schoolCity;
	}

	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	public String getSchoolCityName() {
		return schoolCityName;
	}

	public void setSchoolCityName(String schoolCityName) {
		this.schoolCityName = schoolCityName;
	}

	public String getSchoolArea() {
		return schoolArea;
	}

	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	public String getSchoolRoad() {
		return schoolRoad;
	}

	public void setSchoolRoad(String schoolRoad) {
		this.schoolRoad = schoolRoad;
	}

	public String getExpressType() {
		return expressType;
	}

	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	public String getExpressTypeName() {
		return expressTypeName;
	}

	public void setExpressTypeName(String expressTypeName) {
		this.expressTypeName = expressTypeName;
	}

	public String getExpressProvince() {
		return expressProvince;
	}

	public void setExpressProvince(String expressProvince) {
		this.expressProvince = expressProvince;
	}

	public String getExpressCity() {
		return expressCity;
	}

	public void setExpressCity(String expressCity) {
		this.expressCity = expressCity;
	}

	public String getExpressDistrict() {
		return expressDistrict;
	}

	public void setExpressDistrict(String expressDistrict) {
		this.expressDistrict = expressDistrict;
	}

	public String getExpressDetails() {
		return expressDetails;
	}

	public void setExpressDetails(String expressDetails) {
		this.expressDetails = expressDetails;
	}

	public String getExpressProvinceName() {
		return expressProvinceName;
	}

	public void setExpressProvinceName(String expressProvinceName) {
		this.expressProvinceName = expressProvinceName;
	}

	public String getExpressCityName() {
		return expressCityName;
	}

	public void setExpressCityName(String expressCityName) {
		this.expressCityName = expressCityName;
	}

	public String getExpressDistrictName() {
		return expressDistrictName;
	}

	public void setExpressDistrictName(String expressDistrictName) {
		this.expressDistrictName = expressDistrictName;
	}

	public String getExpressDetailsName() {
		return expressDetailsName;
	}

	public void setExpressDetailsName(String expressDetailsName) {
		this.expressDetailsName = expressDetailsName;
	}

	

}
