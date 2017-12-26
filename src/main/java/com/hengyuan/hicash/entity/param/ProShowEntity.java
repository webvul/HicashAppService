package com.hengyuan.hicash.entity.param;

import java.util.List;

/**
 * 产品展示实体
 * 
 * @author Cary.Liu
 * @createDate 2015-04-22
 *
 */
public class ProShowEntity {

	/** 主键 */
	private String proShowId;

	/** 城市代码 */
	private String cityCode;

	/** 省份 */
	private String province;

	/** 频道ID */
	private String channelId;

	/** 商户产品ID */
	private String merProId;

	/** 产品名称 */
	private String proName;

	/** 产品类型 */
	private String proType;

	/** 产品标题 */
	private String proTitle;

	/** 产品描述 */
	private String proDesc;

	/** 商品价格 */
	private String price;

	/** 最低月还款 */
	private String monthly;

	/** 商户ID */
	private String supplierId;

	/** 商户名称 */
	private String supplierName;

	/** 商户经营城市 */
	private String supplierCity;

	/** 商户经营城市名称 */
	private String supplierCityName;

	/** 展示图片类型 */
	private String picType;

	/** 展示图片地址 */
	private String picPath;

	/** 商户类型 */
	private String merType;

	/** 对应亨元的产品ID */
	private String hyProId;

	/** 产品类型名称 */
	private String proTypeName;

	/** 商品分类(同款商品) */
	private String proClass;

	/** 门店ID */
	private String siteId;

	/** 门店名称 */
	private String siteName;

	/** 门店地址 */
	private String siteAddress;

	/** 行业代码 */
	private String industryCode;

	/** 行业名称 */
	private String industryName;

	/** 商户门店访问url */
	private String supplierUrl;

	/** 申请类型 */
	private String applyType;

	/** 秒杀价格 */
	private String msPrice;

	/** 秒杀分期价格 */
	private String msMonthlyPrice;

	/** 秒杀剩余时间 */
	private String msTime;

	/** 产品图片 */
	private List<MerProPicEntity> proPicList;

	public String getIndustryName() {
		return industryName;
	}

	public void setIndustryName(String industryName) {
		this.industryName = industryName;
	}

	public String getMsPrice() {
		return msPrice;
	}

	public void setMsPrice(String msPrice) {
		this.msPrice = msPrice;
	}

	public String getMsMonthlyPrice() {
		return msMonthlyPrice;
	}

	public void setMsMonthlyPrice(String msMonthlyPrice) {
		this.msMonthlyPrice = msMonthlyPrice;
	}

	public String getMsTime() {
		return msTime;
	}

	public void setMsTime(String msTime) {
		this.msTime = msTime;
	}

	public String getSupplierCityName() {
		return supplierCityName;
	}

	public void setSupplierCityName(String supplierCityName) {
		this.supplierCityName = supplierCityName;
	}

	public String getSupplierCity() {
		return supplierCity;
	}

	public void setSupplierCity(String supplierCity) {
		this.supplierCity = supplierCity;
	}

	public String getApplyType() {
		return applyType;
	}

	public void setApplyType(String applyType) {
		this.applyType = applyType;
	}

	public String getSupplierUrl() {
		return supplierUrl;
	}

	public void setSupplierUrl(String supplierUrl) {
		this.supplierUrl = supplierUrl;
	}

	public String getIndustryCode() {
		return industryCode;
	}

	public void setIndustryCode(String industryCode) {
		this.industryCode = industryCode;
	}

	public String getProClass() {
		return proClass;
	}

	public void setProClass(String proClass) {
		this.proClass = proClass;
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

	public String getSiteAddress() {
		return siteAddress;
	}

	public void setSiteAddress(String siteAddress) {
		this.siteAddress = siteAddress;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProTypeName() {
		return proTypeName;
	}

	public void setProTypeName(String proTypeName) {
		this.proTypeName = proTypeName;
	}

	public String getHyProId() {
		return hyProId;
	}

	public void setHyProId(String hyProId) {
		this.hyProId = hyProId;
	}

	public String getMerType() {
		return merType;
	}

	public void setMerType(String merType) {
		this.merType = merType;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<MerProPicEntity> getProPicList() {
		return proPicList;
	}

	public void setProPicList(List<MerProPicEntity> proPicList) {
		this.proPicList = proPicList;
	}

	public String getProShowId() {
		return proShowId;
	}

	public void setProShowId(String proShowId) {
		this.proShowId = proShowId;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getMerProId() {
		return merProId;
	}

	public void setMerProId(String merProId) {
		this.merProId = merProId;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getProTitle() {
		return proTitle;
	}

	public void setProTitle(String proTitle) {
		this.proTitle = proTitle;
	}

	public String getProDesc() {
		return proDesc;
	}

	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}

	public String getMonthly() {
		return monthly;
	}

	public void setMonthly(String monthly) {
		this.monthly = monthly;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

}
