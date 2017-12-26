package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请2查询返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp2Resp extends ParmResponse{

	/** 返回代码 */
//	private String resultCode;

//	/** 学生类型 */
//	private String stuType;
//
//	private String stuTypeName;
	/** 学校地址--省 */
	private String schoolPro;

	private String schoolProName;

	/** 学校地址--市 */
	private String schoolCity;

	private String schoolCityName;

	/** 学校地址--区 */
	private String schoolArea;

	private String schoolAreaName;

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

	
	/** 学校街道地址 */
	private String schoolRoad;

	/** 客户类型 */
	private String custType;

	/** 客户类型名称 */
	private String custTypeName;

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

	/**
	 * @return the expressProvinceName
	 */
	public String getExpressProvinceName() {
		return expressProvinceName;
	}

	/**
	 * @param expressProvinceName the expressProvinceName to set
	 */
	public void setExpressProvinceName(String expressProvinceName) {
		this.expressProvinceName = expressProvinceName;
	}

	/**
	 * @return the expressCityName
	 */
	public String getExpressCityName() {
		return expressCityName;
	}

	/**
	 * @param expressCityName the expressCityName to set
	 */
	public void setExpressCityName(String expressCityName) {
		this.expressCityName = expressCityName;
	}

	/**
	 * @return the expressDistrictName
	 */
	public String getExpressDistrictName() {
		return expressDistrictName;
	}

	/**
	 * @param expressDistrictName the expressDistrictName to set
	 */
	public void setExpressDistrictName(String expressDistrictName) {
		this.expressDistrictName = expressDistrictName;
	}

	/**
	 * @return the expressDetailsName
	 */
	public String getExpressDetailsName() {
		return expressDetailsName;
	}

	/**
	 * @param expressDetailsName the expressDetailsName to set
	 */
	public void setExpressDetailsName(String expressDetailsName) {
		this.expressDetailsName = expressDetailsName;
	}

	/**
	 * @return the expressProvince
	 */
	public String getExpressProvince() {
		return expressProvince;
	}

	/**
	 * @param expressProvince the expressProvince to set
	 */
	public void setExpressProvince(String expressProvince) {
		this.expressProvince = expressProvince;
	}

	/**
	 * @return the expressCity
	 */
	public String getExpressCity() {
		return expressCity;
	}

	/**
	 * @param expressCity the expressCity to set
	 */
	public void setExpressCity(String expressCity) {
		this.expressCity = expressCity;
	}

	/**
	 * @return the expressDistrict
	 */
	public String getExpressDistrict() {
		return expressDistrict;
	}

	/**
	 * @param expressDistrict the expressDistrict to set
	 */
	public void setExpressDistrict(String expressDistrict) {
		this.expressDistrict = expressDistrict;
	}

	/**
	 * @return the expressDetails
	 */
	public String getExpressDetails() {
		return expressDetails;
	}

	/**
	 * @param expressDetails the expressDetails to set
	 */
	public void setExpressDetails(String expressDetails) {
		this.expressDetails = expressDetails;
	}

	/**
	 * @return the expressType
	 */
	public String getExpressType() {
		return expressType;
	}

	/**
	 * @param expressType the expressType to set
	 */
	public void setExpressType(String expressType) {
		this.expressType = expressType;
	}

	/**
	 * @return the expressTypeName
	 */
	public String getExpressTypeName() {
		return expressTypeName;
	}

	/**
	 * @param expressTypeName the expressTypeName to set
	 */
	public void setExpressTypeName(String expressTypeName) {
		this.expressTypeName = expressTypeName;
	}
//
//	/**
//	 * @return the resultCode
//	 */
//	public String getResultCode() {
//		return resultCode;
//	}
//
//	/**
//	 * @param resultCode the resultCode to set
//	 */
//	public void setResultCode(String resultCode) {
//		this.resultCode = resultCode;
//	}
//
//	/**
//	 * @return the stuType
//	 */
//	public String getStuType() {
//		return stuType;
//	}
//
//	/**
//	 * @param stuType the stuType to set
//	 */
//	public void setStuType(String stuType) {
//		this.stuType = stuType;
//	}
//
//	/**
//	 * @return the stuTypeName
//	 */
//	public String getStuTypeName() {
//		return stuTypeName;
//	}
//
//	/**
//	 * @param stuTypeName the stuTypeName to set
//	 */
//	public void setStuTypeName(String stuTypeName) {
//		this.stuTypeName = stuTypeName;
//	}

	/**
	 * @return the schoolPro
	 */
	public String getSchoolPro() {
		return schoolPro;
	}

	/**
	 * @param schoolPro the schoolPro to set
	 */
	public void setSchoolPro(String schoolPro) {
		this.schoolPro = schoolPro;
	}

	/**
	 * @return the schoolProName
	 */
	public String getSchoolProName() {
		return schoolProName;
	}

	/**
	 * @param schoolProName the schoolProName to set
	 */
	public void setSchoolProName(String schoolProName) {
		this.schoolProName = schoolProName;
	}

	/**
	 * @return the schoolCity
	 */
	public String getSchoolCity() {
		return schoolCity;
	}

	/**
	 * @param schoolCity the schoolCity to set
	 */
	public void setSchoolCity(String schoolCity) {
		this.schoolCity = schoolCity;
	}

	/**
	 * @return the schoolCityName
	 */
	public String getSchoolCityName() {
		return schoolCityName;
	}

	/**
	 * @param schoolCityName the schoolCityName to set
	 */
	public void setSchoolCityName(String schoolCityName) {
		this.schoolCityName = schoolCityName;
	}

	/**
	 * @return the schoolArea
	 */
	public String getSchoolArea() {
		return schoolArea;
	}

	/**
	 * @param schoolArea the schoolArea to set
	 */
	public void setSchoolArea(String schoolArea) {
		this.schoolArea = schoolArea;
	}

	/**
	 * @return the schoolAreaName
	 */
	public String getSchoolAreaName() {
		return schoolAreaName;
	}

	/**
	 * @param schoolAreaName the schoolAreaName to set
	 */
	public void setSchoolAreaName(String schoolAreaName) {
		this.schoolAreaName = schoolAreaName;
	}

	/**
	 * @return the fimilyPro
	 */
	public String getFimilyPro() {
		return fimilyPro;
	}

	/**
	 * @param fimilyPro the fimilyPro to set
	 */
	public void setFimilyPro(String fimilyPro) {
		this.fimilyPro = fimilyPro;
	}

	/**
	 * @return the fimilyProName
	 */
	public String getFimilyProName() {
		return fimilyProName;
	}

	/**
	 * @param fimilyProName the fimilyProName to set
	 */
	public void setFimilyProName(String fimilyProName) {
		this.fimilyProName = fimilyProName;
	}

	/**
	 * @return the fimilyCity
	 */
	public String getFimilyCity() {
		return fimilyCity;
	}

	/**
	 * @param fimilyCity the fimilyCity to set
	 */
	public void setFimilyCity(String fimilyCity) {
		this.fimilyCity = fimilyCity;
	}

	/**
	 * @return the fimilyCityName
	 */
	public String getFimilyCityName() {
		return fimilyCityName;
	}

	/**
	 * @param fimilyCityName the fimilyCityName to set
	 */
	public void setFimilyCityName(String fimilyCityName) {
		this.fimilyCityName = fimilyCityName;
	}

	/**
	 * @return the fimilyArea
	 */
	public String getFimilyArea() {
		return fimilyArea;
	}

	/**
	 * @param fimilyArea the fimilyArea to set
	 */
	public void setFimilyArea(String fimilyArea) {
		this.fimilyArea = fimilyArea;
	}

	/**
	 * @return the fimilyAreaName
	 */
	public String getFimilyAreaName() {
		return fimilyAreaName;
	}

	/**
	 * @param fimilyAreaName the fimilyAreaName to set
	 */
	public void setFimilyAreaName(String fimilyAreaName) {
		this.fimilyAreaName = fimilyAreaName;
	}

	/**
	 * @return the fimilyRoad
	 */
	public String getFimilyRoad() {
		return fimilyRoad;
	}

	/**
	 * @param fimilyRoad the fimilyRoad to set
	 */
	public void setFimilyRoad(String fimilyRoad) {
		this.fimilyRoad = fimilyRoad;
	}

	/**
	 * @return the schoolRoad
	 */
	public String getSchoolRoad() {
		return schoolRoad;
	}

	/**
	 * @param schoolRoad the schoolRoad to set
	 */
	public void setSchoolRoad(String schoolRoad) {
		this.schoolRoad = schoolRoad;
	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the custTypeName
	 */
	public String getCustTypeName() {
		return custTypeName;
	}

	/**
	 * @param custTypeName the custTypeName to set
	 */
	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	
	
}
