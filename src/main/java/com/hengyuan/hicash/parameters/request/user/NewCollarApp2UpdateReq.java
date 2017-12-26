package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端白领提现申请2完善请求参数
 * 
 * @author lihua.Ren
 * @create date 2015-06-17
 *
 */
public class NewCollarApp2UpdateReq extends RequestSequence {

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


	/**单位规模*/
	private String companyScale;
	
	/**单位工作年限*/
	private String companyWorkYear;
	
	/**职位*/
	private String jobduties;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUpdateType() {
		return updateType;
	}

	public void setUpdateType(String updateType) {
		this.updateType = updateType;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getUnitStartDateYear() {
		return unitStartDateYear;
	}

	public void setUnitStartDateYear(String unitStartDateYear) {
		this.unitStartDateYear = unitStartDateYear;
	}

	public String getUnitEndDateMonth() {
		return unitEndDateMonth;
	}

	public void setUnitEndDateMonth(String unitEndDateMonth) {
		this.unitEndDateMonth = unitEndDateMonth;
	}

	public String getInDepartment() {
		return inDepartment;
	}

	public void setInDepartment(String inDepartment) {
		this.inDepartment = inDepartment;
	}

	public String getWorkDate() {
		return workDate;
	}

	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}

	public String getUnitProvince() {
		return unitProvince;
	}

	public void setUnitProvince(String unitProvince) {
		this.unitProvince = unitProvince;
	}

	public String getUnitCity() {
		return unitCity;
	}

	public void setUnitCity(String unitCity) {
		this.unitCity = unitCity;
	}

	public String getUnitDistrict() {
		return unitDistrict;
	}

	public void setUnitDistrict(String unitDistrict) {
		this.unitDistrict = unitDistrict;
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

	public String getExpressAddressType() {
		return expressAddressType;
	}

	public void setExpressAddressType(String expressAddressType) {
		this.expressAddressType = expressAddressType;
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

	public String getUnitDetails() {
		return unitDetails;
	}

	public void setUnitDetails(String unitDetails) {
		this.unitDetails = unitDetails;
	}

	

}
