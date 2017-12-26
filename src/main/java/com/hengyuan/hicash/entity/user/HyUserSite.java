package com.hengyuan.hicash.entity.user;

/**
 * 员工商户售点关系
 * @author Cary.Liu
 *
 */
public class HyUserSite {

	private Integer id;

	/** 工号 */
	private String userNum;

	/** 用户真实姓名 */
	private String userRealName;

	/** 用户名 */
	private String userName;

	/** 商户ID */
	private String merchantId;

	/** 商户名称 */
	private String merchantName;

	/** 售点ID */
	private String siteId;

	/** 售点名称 */
	private String siteName;

	/** 售点所在省 */
	private String siteProId;

	/** 售点所在市 */
	private String siteCityId;

	/** 售点所在省名称 */
	private String siteProName;

	/** 售点所在市名称 */
	private String siteCityName;

	/** 创建时间 */
	private String createDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserNum() {
		return userNum;
	}

	public void setUserNum(String userNum) {
		this.userNum = userNum;
	}

	public String getUserRealName() {
		return userRealName;
	}

	public void setUserRealName(String userRealName) {
		this.userRealName = userRealName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getSiteProId() {
		return siteProId;
	}

	public void setSiteProId(String siteProId) {
		this.siteProId = siteProId;
	}

	public String getSiteCityId() {
		return siteCityId;
	}

	public void setSiteCityId(String siteCityId) {
		this.siteCityId = siteCityId;
	}

	public String getSiteProName() {
		return siteProName;
	}

	public void setSiteProName(String siteProName) {
		this.siteProName = siteProName;
	}

	public String getSiteCityName() {
		return siteCityName;
	}

	public void setSiteCityName(String siteCityName) {
		this.siteCityName = siteCityName;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}