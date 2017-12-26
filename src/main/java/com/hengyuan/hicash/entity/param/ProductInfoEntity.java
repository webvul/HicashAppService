package com.hengyuan.hicash.entity.param;

import java.math.BigDecimal;

/**
 * 产品实体
 * 
 * @author Cary.Liu
 * @create 2014-09-01
 * 
 */
public class ProductInfoEntity {

	/** 产品ID */
	private String productId;

	/** 产品名称 */
	private String productName;

	/** 产品描述 */
	private String productDiscribtion;

	/** 产品总价格 */
	private BigDecimal productPrice;

	/** 产品类型(数据字典) */
	private String productType;

	/** 产品编号 */
	private String productNo;

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

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
