package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * hicash手机端学生提现申请2完善请求参数
 * 
 * @author LiHua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp2UpdateReq extends RequestSequence {
	private static final long serialVersionUID = 7475164653269684647L;

	/** 用户名 */
	private String userName;

	/** 修改类型 */
	private String updateType;

	/** 申请件流水号 */
	private String appNo;

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
	/** 学校地址-省 */
	private String schoolProvince;
	/** 学校地址-城市*/
	private String schoolCity;
	/** 学校地址-区 */
	private String schoolDistrict;

	/** 学校地址-详细 */
	private String schoolDetails;

	/** 家庭地址-省 */
	private String homeProvince;

	/** 家庭地址-市 */
	private String homeCity;

	/** 家庭地址-区 */
	private String homeDistrict;

	/** 家庭地址-详细 */
	private String homeDetails;

	
	/**
	 * @return the expressAddressType
	 */
	public String getExpressAddressType() {
		return expressAddressType;
	}

	/**
	 * @param expressAddressType
	 *            the expressAddressType to set
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
	 * @param userName
	 *            the userName to set
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
	 * @return the schoolDistrict
	 */
	public String getSchoolDistrict() {
		return schoolDistrict;
	}

	/**
	 * @param schoolDistrict
	 *            the schoolDistrict to set
	 */
	public void setSchoolDistrict(String schoolDistrict) {
		this.schoolDistrict = schoolDistrict;
	}

	/**
	 * @param updateType
	 *            the updateType to set
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
	 * @param appNo
	 *            the appNo to set
	 */
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	 /**
	 * @return the schoolProvince
	 */
	 public String getSchoolProvince() {
	 return schoolProvince;
	 }
	
	 /**
	 * @param schoolProvince the schoolProvince to set
	 */
	 public void setSchoolProvince(String schoolProvince) {
	 this.schoolProvince = schoolProvince;
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
	 * @return the schoolDetails
	 */
	public String getSchoolDetails() {
		return schoolDetails;
	}

	/**
	 * @param schoolDetails
	 *            the schoolDetails to set
	 */
	public void setSchoolDetails(String schoolDetails) {
		this.schoolDetails = schoolDetails;
	}

	/**
	 * @return the homeProvince
	 */
	public String getHomeProvince() {
		return homeProvince;
	}

	/**
	 * @param homeProvince
	 *            the homeProvince to set
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
	 * @param homeCity
	 *            the homeCity to set
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
	 * @param homeDistrict
	 *            the homeDistrict to set
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
	 * @param homeDetails
	 *            the homeDetails to set
	 */
	public void setHomeDetails(String homeDetails) {
		this.homeDetails = homeDetails;
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
	
}
