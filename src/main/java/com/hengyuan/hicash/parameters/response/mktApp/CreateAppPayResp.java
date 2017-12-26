package com.hengyuan.hicash.parameters.response.mktApp;

import java.math.BigDecimal;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * 创建申请件返回参数对象
 * 
 * @author Cary.Liu
 * @create 2014-09-25
 * 
 */
public class CreateAppPayResp extends ParmResponse {

	/** 申请件号 */
	private String appPayNo;

	private BigDecimal monthPayMent;

	/** 月还款 */
	private String monthPayAmount;

	/** 还款期数 */
	private String monthInstallMent;

	/** 申请金额 */
	private String applyAmount;

	/** 产品名称 */
	private String proName;

	/** 收入金额 */
	private String firstMoney;

	/** 商户名称 */
	private String supplierName;

	/** 售点名称 */
	private String siteName;

	/** 2016新年活动-是否有抽奖机会 */
	private String lotteryFlag;

	public String getLotteryFlag() {
		return lotteryFlag;
	}

	public void setLotteryFlag(String lotteryFlag) {
		this.lotteryFlag = lotteryFlag;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public String getFirstMoney() {
		return firstMoney;
	}

	public void setFirstMoney(String firstMoney) {
		this.firstMoney = firstMoney;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getApplyAmount() {
		return applyAmount;
	}

	public void setApplyAmount(String applyAmount) {
		this.applyAmount = applyAmount;
	}

	public BigDecimal getMonthPayMent() {
		return monthPayMent;
	}

	public void setMonthPayMent(BigDecimal monthPayMent) {
		this.monthPayMent = monthPayMent;
	}

	public String getAppPayNo() {
		return appPayNo;
	}

	public void setAppPayNo(String appPayNo) {
		this.appPayNo = appPayNo;
	}

	public String getMonthPayAmount() {
		return monthPayAmount;
	}

	public void setMonthPayAmount(String monthPayAmount) {
		this.monthPayAmount = monthPayAmount;
	}

	public String getMonthInstallMent() {
		return monthInstallMent;
	}

	public void setMonthInstallMent(String monthInstallMent) {
		this.monthInstallMent = monthInstallMent;
	}

}
