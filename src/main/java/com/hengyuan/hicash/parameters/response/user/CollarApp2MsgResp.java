package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;



/**
 * 白领获取资料响应参数
 * 
 * @author LiHua.Ren
 * @create date 2015-05-23
 *
 */
public class CollarApp2MsgResp extends ParmResponse {

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

	/** 月消费 */
	private String monthlyConsumption;
	
	/**单位规模*/
	private String companyScale;
	
	/**单位工作年限*/
	private String companyWorkYear;
	
	/**职位*/
	private String jobduties;
	
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
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * @return the workerDep
	 */
	public String getWorkerDep() {
		return workerDep;
	}

	/**
	 * @param workerDep the workerDep to set
	 */
	public void setWorkerDep(String workerDep) {
		this.workerDep = workerDep;
	}

	/**
	 * @return the workerTime
	 */
	public String getWorkerTime() {
		return workerTime;
	}

	/**
	 * @param workerTime the workerTime to set
	 */
	public void setWorkerTime(String workerTime) {
		this.workerTime = workerTime;
	}

	/**
	 * @return the workerTimeName
	 */
	public String getWorkerTimeName() {
		return workerTimeName;
	}

	/**
	 * @param workerTimeName the workerTimeName to set
	 */
	public void setWorkerTimeName(String workerTimeName) {
		this.workerTimeName = workerTimeName;
	}

	/**
	 * @return the workerYear
	 */
	public String getWorkerYear() {
		return workerYear;
	}

	/**
	 * @param workerYear the workerYear to set
	 */
	public void setWorkerYear(String workerYear) {
		this.workerYear = workerYear;
	}

	/**
	 * @return the workerMonth
	 */
	public String getWorkerMonth() {
		return workerMonth;
	}

	/**
	 * @param workerMonth the workerMonth to set
	 */
	public void setWorkerMonth(String workerMonth) {
		this.workerMonth = workerMonth;
	}

	/**
	 * @return the workProvince
	 */
	public String getWorkProvince() {
		return workProvince;
	}

	/**
	 * @param workProvince the workProvince to set
	 */
	public void setWorkProvince(String workProvince) {
		this.workProvince = workProvince;
	}

	/**
	 * @return the workProvinceName
	 */
	public String getWorkProvinceName() {
		return workProvinceName;
	}

	/**
	 * @param workProvinceName the workProvinceName to set
	 */
	public void setWorkProvinceName(String workProvinceName) {
		this.workProvinceName = workProvinceName;
	}

	/**
	 * @return the workCity
	 */
	public String getWorkCity() {
		return workCity;
	}

	/**
	 * @param workCity the workCity to set
	 */
	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	/**
	 * @return the workCityName
	 */
	public String getWorkCityName() {
		return workCityName;
	}

	/**
	 * @param workCityName the workCityName to set
	 */
	public void setWorkCityName(String workCityName) {
		this.workCityName = workCityName;
	}

	/**
	 * @return the workArea
	 */
	public String getWorkArea() {
		return workArea;
	}

	/**
	 * @param workArea the workArea to set
	 */
	public void setWorkArea(String workArea) {
		this.workArea = workArea;
	}

	/**
	 * @return the workAreaName
	 */
	public String getWorkAreaName() {
		return workAreaName;
	}

	/**
	 * @param workAreaName the workAreaName to set
	 */
	public void setWorkAreaName(String workAreaName) {
		this.workAreaName = workAreaName;
	}

	/**
	 * @return the workRoad
	 */
	public String getWorkRoad() {
		return workRoad;
	}

	/**
	 * @param workRoad the workRoad to set
	 */
	public void setWorkRoad(String workRoad) {
		this.workRoad = workRoad;
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
	 * @return the unitPhoneArea
	 */
	public String getUnitPhoneArea() {
		return unitPhoneArea;
	}

	/**
	 * @param unitPhoneArea the unitPhoneArea to set
	 */
	public void setUnitPhoneArea(String unitPhoneArea) {
		this.unitPhoneArea = unitPhoneArea;
	}

	/**
	 * @return the unitPhoneNum
	 */
	public String getUnitPhoneNum() {
		return unitPhoneNum;
	}

	/**
	 * @param unitPhoneNum the unitPhoneNum to set
	 */
	public void setUnitPhoneNum(String unitPhoneNum) {
		this.unitPhoneNum = unitPhoneNum;
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
	 * @return the monthlyIncome
	 */
	public String getMonthlyIncome() {
		return monthlyIncome;
	}

	/**
	 * @param monthlyIncome the monthlyIncome to set
	 */
	public void setMonthlyIncome(String monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}

	/**
	 * @return the monthlyConsumption
	 */
	public String getMonthlyConsumption() {
		return monthlyConsumption;
	}

	/**
	 * @param monthlyConsumption the monthlyConsumption to set
	 */
	public void setMonthlyConsumption(String monthlyConsumption) {
		this.monthlyConsumption = monthlyConsumption;
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
