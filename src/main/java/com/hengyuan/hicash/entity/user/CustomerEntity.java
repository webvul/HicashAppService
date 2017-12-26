package com.hengyuan.hicash.entity.user;

/**
 * 客户信息查询实体
 * 
 * @author Cary.Liu
 * @create date 2014-07-26
 */
public class CustomerEntity {

	private String userName;

	private String realName;

	private String custType;

	private String mobile;

	private String identityNo;

	private String education;// 最高学历

	/* 2015-03-16 新增 author: Cary.Liu */

	/** 现居住地址-省 */
	private String nowProvince;

	/** 现居住地址-市 */
	private String nowCity;

	/** 现居住地址-区 */
	private String nowArea;

	/** 现居住地址-详细地址 */
	private String nowAddress;

	/** 用户手持身份证正面照 */
	private String userIdcardFrontUrl;

	/** 身份证正面照 */
	private String idcardFrontUrl;

	/** 身份证反面照 */
	private String idcardVersoUrl;

	/* 小图 */
	/** 用户手持身份证正面照 */
	private String userIdcardFrontUrlThum;

	/** 身份证正面照 */
	private String idcardFrontUrlThum;

	/** 身份证反面照 */
	private String idcardVersoUrlThum;

	/** 学生证封面ZL04 */
	private String stuCardFrontUrl;
	/** 个人照片及基本信息ZL05 */
	private String stuPhotoInfoUrl;
	/** 注册登记信息ZL06 */
	private String stuRegistInfoUrl;
	/** 校园卡正面ZL07 */
	private String schoolCardFrontUrl;
	/** 校园卡反面ZL08 */
	private String schoolCardVersoUrl;

	/** 学生证封面ZL04 */
	private String stuCardFrontUrlThum;
	/** 个人照片及基本信息ZL05 */
	private String stuPhotoInfoUrlThum;
	/** 注册登记信息ZL06 */
	private String stuRegistInfoUrlThum;
	/** 校园卡正面ZL07 */
	private String schoolCardFrontUrlThum;
	/** 校园卡反面ZL08 */
	private String schoolCardVersoUrlThum;

	/** 快递地址-省份 */
	private String expressProvince;

	/** 快递地址-城市 */
	private String expressCity;

	/** 快递地址-区域 */
	private String expressArea;

	/** 快递地址-详情 */
	private String expressDetail;

	/* 蓝领业务新增 */

	/** 是否为蓝领用户 */
	private String lanUserFlag;

	/** 邀请码（业务员工号） */
	private String inveteCode;

	/** 门店号 */
	private String storeCode;

	/** 用户现场照大图url */
	private String userScenepicUrl;

	/** 用户现场照小图url */
	private String userScenepicThumUrl;

	/** 是否已经抽奖 */
	private String regLotteryFlag;

	/** 抽奖红包金额 */
	private String regCashRedPac;
	
	private String emailAdress;
	/**借款用途*/
	private String loanPurpose;

	public String getLanUserFlag() {
		return lanUserFlag;
	}

	public void setLanUserFlag(String lanUserFlag) {
		this.lanUserFlag = lanUserFlag;
	}

	public String getInveteCode() {
		return inveteCode;
	}

	public void setInveteCode(String inveteCode) {
		this.inveteCode = inveteCode;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public String getUserScenepicUrl() {
		return userScenepicUrl;
	}

	public void setUserScenepicUrl(String userScenepicUrl) {
		this.userScenepicUrl = userScenepicUrl;
	}

	public String getUserScenepicThumUrl() {
		return userScenepicThumUrl;
	}

	public void setUserScenepicThumUrl(String userScenepicThumUrl) {
		this.userScenepicThumUrl = userScenepicThumUrl;
	}

	public String getRegLotteryFlag() {
		return regLotteryFlag;
	}

	public void setRegLotteryFlag(String regLotteryFlag) {
		this.regLotteryFlag = regLotteryFlag;
	}

	public String getRegCashRedPac() {
		return regCashRedPac;
	}

	public void setRegCashRedPac(String regCashRedPac) {
		this.regCashRedPac = regCashRedPac;
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

	public String getExpressArea() {
		return expressArea;
	}

	public void setExpressArea(String expressArea) {
		this.expressArea = expressArea;
	}

	public String getExpressDetail() {
		return expressDetail;
	}

	public void setExpressDetail(String expressDetail) {
		this.expressDetail = expressDetail;
	}

	public String getUserIdcardFrontUrl() {
		return userIdcardFrontUrl;
	}

	public void setUserIdcardFrontUrl(String userIdcardFrontUrl) {
		this.userIdcardFrontUrl = userIdcardFrontUrl;
	}

	public String getIdcardFrontUrl() {
		return idcardFrontUrl;
	}

	public void setIdcardFrontUrl(String idcardFrontUrl) {
		this.idcardFrontUrl = idcardFrontUrl;
	}

	public String getIdcardVersoUrl() {
		return idcardVersoUrl;
	}

	public void setIdcardVersoUrl(String idcardVersoUrl) {
		this.idcardVersoUrl = idcardVersoUrl;
	}

	public String getUserIdcardFrontUrlThum() {
		return userIdcardFrontUrlThum;
	}

	public void setUserIdcardFrontUrlThum(String userIdcardFrontUrlThum) {
		this.userIdcardFrontUrlThum = userIdcardFrontUrlThum;
	}

	public String getIdcardFrontUrlThum() {
		return idcardFrontUrlThum;
	}

	public void setIdcardFrontUrlThum(String idcardFrontUrlThum) {
		this.idcardFrontUrlThum = idcardFrontUrlThum;
	}

	public String getIdcardVersoUrlThum() {
		return idcardVersoUrlThum;
	}

	public void setIdcardVersoUrlThum(String idcardVersoUrlThum) {
		this.idcardVersoUrlThum = idcardVersoUrlThum;
	}

	public String getNowProvince() {
		return nowProvince;
	}

	public void setNowProvince(String nowProvince) {
		this.nowProvince = nowProvince;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	/**
	 * @return the stuCardFrontUrl
	 */
	public String getStuCardFrontUrl() {
		return stuCardFrontUrl;
	}

	/**
	 * @param stuCardFrontUrl
	 *            the stuCardFrontUrl to set
	 */
	public void setStuCardFrontUrl(String stuCardFrontUrl) {
		this.stuCardFrontUrl = stuCardFrontUrl;
	}

	/**
	 * @return the stuPhotoInfoUrl
	 */
	public String getStuPhotoInfoUrl() {
		return stuPhotoInfoUrl;
	}

	/**
	 * @param stuPhotoInfoUrl
	 *            the stuPhotoInfoUrl to set
	 */
	public void setStuPhotoInfoUrl(String stuPhotoInfoUrl) {
		this.stuPhotoInfoUrl = stuPhotoInfoUrl;
	}

	/**
	 * @return the stuRegistInfoUrl
	 */
	public String getStuRegistInfoUrl() {
		return stuRegistInfoUrl;
	}

	/**
	 * @param stuRegistInfoUrl
	 *            the stuRegistInfoUrl to set
	 */
	public void setStuRegistInfoUrl(String stuRegistInfoUrl) {
		this.stuRegistInfoUrl = stuRegistInfoUrl;
	}

	/**
	 * @return the schoolCardFrontUrl
	 */
	public String getSchoolCardFrontUrl() {
		return schoolCardFrontUrl;
	}

	/**
	 * @param schoolCardFrontUrl
	 *            the schoolCardFrontUrl to set
	 */
	public void setSchoolCardFrontUrl(String schoolCardFrontUrl) {
		this.schoolCardFrontUrl = schoolCardFrontUrl;
	}

	/**
	 * @return the schoolCardVersoUrl
	 */
	public String getSchoolCardVersoUrl() {
		return schoolCardVersoUrl;
	}

	/**
	 * @param schoolCardVersoUrl
	 *            the schoolCardVersoUrl to set
	 */
	public void setSchoolCardVersoUrl(String schoolCardVersoUrl) {
		this.schoolCardVersoUrl = schoolCardVersoUrl;
	}

	/**
	 * @return the stuCardFrontUrlThum
	 */
	public String getStuCardFrontUrlThum() {
		return stuCardFrontUrlThum;
	}

	/**
	 * @param stuCardFrontUrlThum
	 *            the stuCardFrontUrlThum to set
	 */
	public void setStuCardFrontUrlThum(String stuCardFrontUrlThum) {
		this.stuCardFrontUrlThum = stuCardFrontUrlThum;
	}

	/**
	 * @return the stuPhotoInfoUrlThum
	 */
	public String getStuPhotoInfoUrlThum() {
		return stuPhotoInfoUrlThum;
	}

	/**
	 * @param stuPhotoInfoUrlThum
	 *            the stuPhotoInfoUrlThum to set
	 */
	public void setStuPhotoInfoUrlThum(String stuPhotoInfoUrlThum) {
		this.stuPhotoInfoUrlThum = stuPhotoInfoUrlThum;
	}

	/**
	 * @return the stuRegistInfoUrlThum
	 */
	public String getStuRegistInfoUrlThum() {
		return stuRegistInfoUrlThum;
	}

	/**
	 * @param stuRegistInfoUrlThum
	 *            the stuRegistInfoUrlThum to set
	 */
	public void setStuRegistInfoUrlThum(String stuRegistInfoUrlThum) {
		this.stuRegistInfoUrlThum = stuRegistInfoUrlThum;
	}

	/**
	 * @return the schoolCardFrontUrlThum
	 */
	public String getSchoolCardFrontUrlThum() {
		return schoolCardFrontUrlThum;
	}

	/**
	 * @param schoolCardFrontUrlThum
	 *            the schoolCardFrontUrlThum to set
	 */
	public void setSchoolCardFrontUrlThum(String schoolCardFrontUrlThum) {
		this.schoolCardFrontUrlThum = schoolCardFrontUrlThum;
	}

	/**
	 * @return the schoolCardVersoUrlThum
	 */
	public String getSchoolCardVersoUrlThum() {
		return schoolCardVersoUrlThum;
	}

	/**
	 * @param schoolCardVersoUrlThum
	 *            the schoolCardVersoUrlThum to set
	 */
	public void setSchoolCardVersoUrlThum(String schoolCardVersoUrlThum) {
		this.schoolCardVersoUrlThum = schoolCardVersoUrlThum;
	}

	public String getEmailAdress() {
		return emailAdress;
	}

	public void setEmailAdress(String emailAdress) {
		this.emailAdress = emailAdress;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	
	

}
