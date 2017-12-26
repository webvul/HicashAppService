package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 更新蓝领用户资料
 * 
 * @author Cary.Liu
 * @createDate 2016-03-02
 *
 */
public class UpdateLanUserInfoReq extends RequestSequence {

	private static final long serialVersionUID = 1L;

	private String userName;

	/** 邀请码 */
	private String inviteCode;

	/** 门店号 */
	private String storeCode;

	/** 用户现场照大图url */
	private String userScenepicUrl;

	/** 用户现场照小图url */
	private String userScenepicThumUrl;

	/** 是否已有门店 1：是，0：否 */
	private String isHaveStore;

	/** 单位名称 */
	private String unitName;

	/** 店名 */
	private String storeName;

	/** 所在省份 */
	private String province;

	/** 所在城市 */
	private String city;

	/** 详细地址 */
	private String address;

	/** 路牌号 */
	private String roadNo;

	/** 经营权限 */
	private String operatePower;

	/** 经营时间 */
	private String operateTime;

	/** 法人姓名 */
	private String legalName;

	/** 单位电话 */
	private String unitPhone;

	/** 单位照片url */
	private String unitPicUrl;

	/** 位置定位照片url */
	private String locaPicUrl;

	/** 门店照片url */
	private String storePicUrl;

	/** 工商网查询截图url */
	private String storePicUrl1;

	/** 门店照片url */
	private String storePicUrl2;

	/** 门店照片url */
	private String storePicUrl3;

	public String getStorePicUrl3() {
		return storePicUrl3;
	}

	public void setStorePicUrl3(String storePicUrl3) {
		this.storePicUrl3 = storePicUrl3;
	}

	public String getStorePicUrl1() {
		return storePicUrl1;
	}

	public void setStorePicUrl1(String storePicUrl1) {
		this.storePicUrl1 = storePicUrl1;
	}

	public String getStorePicUrl2() {
		return storePicUrl2;
	}

	public void setStorePicUrl2(String storePicUrl2) {
		this.storePicUrl2 = storePicUrl2;
	}

	public String getUnitPicUrl() {
		return unitPicUrl;
	}

	public void setUnitPicUrl(String unitPicUrl) {
		this.unitPicUrl = unitPicUrl;
	}

	public String getLocaPicUrl() {
		return locaPicUrl;
	}

	public void setLocaPicUrl(String locaPicUrl) {
		this.locaPicUrl = locaPicUrl;
	}

	public String getStorePicUrl() {
		return storePicUrl;
	}

	public void setStorePicUrl(String storePicUrl) {
		this.storePicUrl = storePicUrl;
	}

	public String getIsHaveStore() {
		return isHaveStore;
	}

	public void setIsHaveStore(String isHaveStore) {
		this.isHaveStore = isHaveStore;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRoadNo() {
		return roadNo;
	}

	public void setRoadNo(String roadNo) {
		this.roadNo = roadNo;
	}

	public String getOperatePower() {
		return operatePower;
	}

	public void setOperatePower(String operatePower) {
		this.operatePower = operatePower;
	}

	public String getOperateTime() {
		return operateTime;
	}

	public void setOperateTime(String operateTime) {
		this.operateTime = operateTime;
	}

	public String getLegalName() {
		return legalName;
	}

	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}

	public String getUnitPhone() {
		return unitPhone;
	}

	public void setUnitPhone(String unitPhone) {
		this.unitPhone = unitPhone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getInviteCode() {
		return inviteCode;
	}

	public void setInviteCode(String inviteCode) {
		this.inviteCode = inviteCode;
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

}
