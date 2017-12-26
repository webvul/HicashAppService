package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端白领提现申请2查询返回信息
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp2MsgResp extends ParmResponse {
	
	/** 客户类型 */
	private String custType;
	/** 客户类型名称 */
	private String custTypeName;
	
	/** 单位名称 */
	private String jobName;
	/** 任职部门 */
	private String workerDep;

	/** 工作年限 */
	private String workerTime;

	/** 工作年限名称 */
	private String workerTimeName;
	
	/** 入职时间--年 */
	private String workerYear;

	/** 入职时间--月 */
	private String workerMonth;

	
	

	/** 单位地址--省 */
	private String workProvince;

	/** 单位地址--省名称 */
	private String workProvinceName;

	/** 单位地址--市 */
	private String workCity;

	private String workCityName;

	/** 单位地址--区 */
	private String workArea;

	private String workAreaName;

	/** 单位街道地址 */
	private String workRoad;

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
	/** 单位电话-区号 */
	private String unitPhoneArea;

	/** 单位电话-号码 */
	private String unitPhoneNum;
	/** 月收入 */
	private String monthlyIncome;

	
	
	/**单位规模*/
	private String companyScale;
	
	/**单位工作年限*/
	private String companyWorkYear;
	
	/**职位*/
	private String jobduties;

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

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getWorkerDep() {
		return workerDep;
	}

	public void setWorkerDep(String workerDep) {
		this.workerDep = workerDep;
	}

	public String getWorkerTime() {
		return workerTime;
	}

	public void setWorkerTime(String workerTime) {
		this.workerTime = workerTime;
	}

	public String getWorkerTimeName() {
		return workerTimeName;
	}

	public void setWorkerTimeName(String workerTimeName) {
		this.workerTimeName = workerTimeName;
	}

	public String getWorkerYear() {
		return workerYear;
	}

	public void setWorkerYear(String workerYear) {
		this.workerYear = workerYear;
	}

	public String getWorkerMonth() {
		return workerMonth;
	}

	public void setWorkerMonth(String workerMonth) {
		this.workerMonth = workerMonth;
	}

	public String getWorkProvince() {
		return workProvince;
	}

	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}

	public String getWorkProvinceName() {
		return workProvinceName;
	}

	public void setWorkProvinceName(String workProvinceName) {
		this.workProvinceName = workProvinceName;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getWorkCityName() {
		return workCityName;
	}

	public void setWorkCityName(String workCityName) {
		this.workCityName = workCityName;
	}

	public String getWorkArea() {
		return workArea;
	}

	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	public String getWorkAreaName() {
		return workAreaName;
	}

	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}

	public String getWorkRoad() {
		return workRoad;
	}

	public void setWorkRoad(String workRoad) {
		this.workRoad = workRoad;
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

	public String getUnitPhoneArea() {
		return unitPhoneArea;
	}

	public void setUnitPhoneArea(String unitPhoneArea) {
		this.unitPhoneArea = unitPhoneArea;
	}

	public String getUnitPhoneNum() {
		return unitPhoneNum;
	}

	public void setUnitPhoneNum(String unitPhoneNum) {
		this.unitPhoneNum = unitPhoneNum;
	}

	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	

	public String getCompanyScale() {
		return companyScale;
	}

	public void setCompanyScale(String companyScale) {
		this.companyScale = companyScale;
	}

	public String getCompanyWorkYear() {
		return companyWorkYear;
	}

	public void setCompanyWorkYear(String companyWorkYear) {
		this.companyWorkYear = companyWorkYear;
	}

	public String getJobduties() {
		return jobduties;
	}

	public void setJobduties(String jobduties) {
		this.jobduties = jobduties;
	}
	
	
	
}
