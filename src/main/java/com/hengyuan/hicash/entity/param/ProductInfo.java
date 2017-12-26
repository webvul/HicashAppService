package com.hengyuan.hicash.entity.param;

import java.math.BigDecimal;

/**
 * 
 * 产品主表信息
 * 
 * @author Mary.Luo
 * 
 */
public class ProductInfo {

	/**
	 * 产品主键ID
	 */
	private Integer proId;

	/**
	 * 产品编号
	 */
	private String productNo;

	/**
	 * 产品名称
	 */
	private String productName;

	/**
	 * 产品描述
	 */
	private String productDiscribtion;

	/**
	 * 产品总价格
	 */
	private BigDecimal productPrice;

	/**
	 * 产品销量
	 */
	private Integer productSale;

	/**
	 * 产品类型(数据字典)
	 */
	private String productType;

	/**
	 * 展示图片地址
	 */
	private String parameterPic;

	/**
	 * 首页图片展示地址
	 */
	private String picIndex;

	/**
	 * 缩略图地址
	 */
	private String picNarrow;

	/**
	 * 展示图片
	 */
	private String picShow;

	/**
	 * 申请页面显示图
	 */
	private String picApp;

	public ProductInfo() {
	}

	public Integer getProId() {
		return proId;
	}

	public void setProId(Integer proId) {
		this.proId = proId;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDiscribtion() {
		return productDiscribtion;
	}

	public void setProductDiscribtion(String productDiscribtion) {
		this.productDiscribtion = productDiscribtion;
	}

	public BigDecimal getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(BigDecimal productPrice) {
		this.productPrice = productPrice;
	}

	public Integer getProductSale() {
		return productSale;
	}

	public void setProductSale(Integer productSale) {
		this.productSale = productSale;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getParameterPic() {
		return parameterPic;
	}

	public void setParameterPic(String parameterPic) {
		this.parameterPic = parameterPic;
	}

	public String getPicIndex() {
		return picIndex;
	}

	public void setPicIndex(String picIndex) {
		this.picIndex = picIndex;
	}

	public String getPicNarrow() {
		return picNarrow;
	}

	public void setPicNarrow(String picNarrow) {
		this.picNarrow = picNarrow;
	}

	public String getPicShow() {
		return picShow;
	}

	public void setPicShow(String picShow) {
		this.picShow = picShow;
	}

	public String getPicApp() {
		return picApp;
	}

	public void setPicApp(String picApp) {
		this.picApp = picApp;
	}

}
