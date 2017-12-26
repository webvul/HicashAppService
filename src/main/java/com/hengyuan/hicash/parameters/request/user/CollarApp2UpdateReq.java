package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;



/**
 * @author Administrator
 *
 */
public class CollarApp2UpdateReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	/** 用户名 */
	private String userName;

	/** 修改类型 */
	private String updateType;

	/** 申请件流水号 */
	private String appNo;

	/*
	 * 个人信息
	 */
	/** 现单位名称 */
	private String unitName;
	
	/** 现单位入职时间-年 */
	private String unitStartDateYear;

	/** 现单位入职时间-月 */
	private String unitEndDateMonth;
	/** 入职部门 */
	private String inDepartment;

	/** 总工作年限 */
	private String workDate;
	/** 单位地址-省 */
	private String unitProvince;

	/** 单位地址-市 */
	private String unitCity;

	/** 单位地址-区 */
	private String unitDistrict;

	/** 单位地址-详细地址 */
	private String unitDetails;
	/** 家庭地址-省 */
	private String homeProvince;

	/** 家庭地址-市 */
	private String homeCity;

	/** 家庭地址-区 */
	private String homeDistrict;

	/** 家庭地址-详细 */
	private String homeDetails;
	/*
	 * 合同快递地址
	 */

	/** 合同快递地址-省 */
	private String expressProvince;

	/** 合同快递地址-市 */
	private String expressCity;

	/** 合同快递地址-区 */
	private String expressDistrict;

	/** 合同快递地址-详细 */
	private String expressDetails;
	/** 合同邮寄地址类型 */
	private String expressAddressType;
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
	 * @return the expressAddressType
	 */
	public String getExpressAddressType() {
		return expressAddressType;
	}

	/**
	 * @param expressAddressType the expressAddressType to set
	 */
	public void setExpressAddressType(String expressAddressType) {
		this.expressAddressType = expressAddressType;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the updateType
	 */
	public String getUpdateType() {
		return updateType;
	}

	/**
	 * @param updateType the updateType to set
	 */
	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	/**
	 * @return the appNo
	 */
	public String getAppNo() {
		return appNo;
	}

	/**
	 * @param appNo the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName() {
		return unitName;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	/**
	 * @return the unitStartDateYear
	 */
	public String getUnitStartDateYear() {
		return unitStartDateYear;
	}

	/**
	 * @param unitStartDateYear the unitStartDateYear to set
	 */
	public void setUnitStartDateYear(String unitStartDateYear) {
		this.unitStartDateYear = unitStartDateYear;
	}

	/**
	 * @return the unitEndDateMonth
	 */
	public String getUnitEndDateMonth() {
		return unitEndDateMonth;
	}

	/**
	 * @param unitEndDateMonth the unitEndDateMonth to set
	 */
	public void setUnitEndDateMonth(String unitEndDateMonth) {
		this.unitEndDateMonth = unitEndDateMonth;
	}

	/**
	 * @return the inDepartment
	 */
	public String getInDepartment() {
		return inDepartment;
	}

	/**
	 * @param inDepartment the inDepartment to set
	 */
	public void setInDepartment(String inDepartment) {
		this.inDepartment = inDepartment;
	}

	/**
	 * @return the workDate
	 */
	public String getWorkDate() {
		return workDate;
	}

	/**
	 * @param workDate the workDate to set
	 */
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	/**
	 * @return the unitProvince
	 */
	public String getUnitProvince() {
		return unitProvince;
	}

	/**
	 * @param unitProvince the unitProvince to set
	 */
	public void setUnitProvince(String unitProvince) {
		this.unitProvince = unitProvince;
	}

	/**
	 * @return the unitCity
	 */
	public String getUnitCity() {
		return unitCity;
	}

	/**
	 * @param unitCity the unitCity to set
	 */
	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}

	/**
	 * @return the unitDistrict
	 */
	public String getUnitDistrict() {
		return unitDistrict;
	}

	/**
	 * @param unitDistrict the unitDistrict to set
	 */
	public void setUnitDistrict(String unitDistrict) {
		this.unitDistrict = unitDistrict;
	}

	/**
	 * @return the unitDetails
	 */
	public String getUnitDetails() {
		return unitDetails;
	}

	/**
	 * @param unitDetails the unitDetails to set
	 */
	public void setUnitDetails(String unitDetails) {
		this.unitDetails = unitDetails;
	}

	/**
	 * @return the homeProvince
	 */
	public String getHomeProvince() {
		return homeProvince;
	}

	/**
	 * @param homeProvince the homeProvince to set
	 */
	public void setHomeProvince(String homeProvince) {
		this.homeProvince = homeProvince;
	}

	/**
	 * @return the homeCity
	 */
	public String getHomeCity() {
		return homeCity;
	}

	/**
	 * @param homeCity the homeCity to set
	 */
	public void setHomeCity(String homeCity) {
		this.homeCity = homeCity;
	}

	/**
	 * @return the homeDistrict
	 */
	public String getHomeDistrict() {
		return homeDistrict;
	}

	/**
	 * @param homeDistrict the homeDistrict to set
	 */
	public void setHomeDistrict(String homeDistrict) {
		this.homeDistrict = homeDistrict;
	}

	/**
	 * @return the homeDetails
	 */
	public String getHomeDetails() {
		return homeDetails;
	}

	/**
	 * @param homeDetails the homeDetails to set
	 */
	public void setHomeDetails(String homeDetails) {
		this.homeDetails = homeDetails;
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
