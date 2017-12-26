package com.hengyuan.hicash.parameters.response.user;

import java.math.BigDecimal;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * 贷款金额计算返回参数
 * 
 * @author Andy.Niu
 * @create date 2014-07-15
 */
public class LoanAmtCalResp extends ParmResponse {

	/** 首付金额 */
	private BigDecimal firstPayMent;

	//准vip按钮文字
	private String btn_str;
	//准vip弹框文字
	private String box_msg;
	//准vip弹框按钮文字
	private String box_btn;
	
	/** 月还款额(月还款本息+月管理费) */
	private BigDecimal monthPayMent;

	/** 还款期数 */
	private Integer payMentPeriod;

	/** 服务费 */
	private BigDecimal serviceCharge;

	/** 实际借款本金 */
	private BigDecimal loanPrincipal;

	/** 月管理费 */
	private BigDecimal monthFee;

	/** 月还款本息 */
	private BigDecimal monthPayback;

	/** 贷款产品id */
	private Integer loanProduct;

	/***/
	private BigDecimal finalAmount;

	private BigDecimal mobServ;
	private BigDecimal cardServ;
	private BigDecimal sfServ;
	private BigDecimal zxServ;
	private BigDecimal cuServ;
	private BigDecimal whServ;
	private BigDecimal infoServ;
	private BigDecimal everMoth;
	private BigDecimal perMonthPay;
	private BigDecimal serviceMonthPay;
	
	//应付保费 应付保费=每期保费*期数
	private BigDecimal jCopePremium;
	
	//保险金额 对应保险金额=借款本金
	private BigDecimal jInsuredAmount;
	
	//每月保费   每期保费=借款本金*保险费率
	private BigDecimal jMerPermium;

	
	//应付保费 应付保费=每期保费*期数
	private BigDecimal wCopePremium;
		
	//保险金额 对应保险金额=应付保费*1300 深圳众诚泰保  
	private BigDecimal wInsuredAmount;
		
	//每月保费   每期保费=借款本金*保险费率
	private BigDecimal wMerPermium;

	//总信息服务费
	private BigDecimal totMoth;

	public Integer getLoanProduct() {
		return loanProduct;
	}

	public void setLoanProduct(Integer loanProduct) {
		this.loanProduct = loanProduct;
	}

	public void clear() {
		this.firstPayMent = null;
		this.monthPayMent = null;
		this.payMentPeriod = null;
		this.serviceCharge = null;
		this.loanPrincipal = null;
	}

	public BigDecimal getLoanPrincipal() {
		return loanPrincipal;
	}

	public void setLoanPrincipal(BigDecimal loanPrincipal) {
		this.loanPrincipal = loanPrincipal;
	}

	public BigDecimal getFirstPayMent() {
		return firstPayMent;
	}

	public void setFirstPayMent(BigDecimal firstPayMent) {
		this.firstPayMent = firstPayMent;
	}

	public BigDecimal getMonthPayMent() {
		return monthPayMent;
	}

	public void setMonthPayMent(BigDecimal monthPayMent) {
		this.monthPayMent = monthPayMent;
	}

	public Integer getPayMentPeriod() {
		return payMentPeriod;
	}

	public void setPayMentPeriod(Integer payMentPeriod) {
		this.payMentPeriod = payMentPeriod;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public String getBtn_str() {
		return btn_str;
	}

	public void setBtn_str(String btn_str) {
		this.btn_str = btn_str;
	}

	public String getBox_msg() {
		return box_msg;
	}

	public void setBox_msg(String box_msg) {
		this.box_msg = box_msg;
	}

	public String getBox_btn() {
		return box_btn;
	}

	public void setBox_btn(String box_btn) {
		this.box_btn = box_btn;
	}

	public BigDecimal getMonthFee() {
		return monthFee;
	}

	public void setMonthFee(BigDecimal monthFee) {
		this.monthFee = monthFee;
	}

	public BigDecimal getMonthPayback() {
		return monthPayback;
	}

	public void setMonthPayback(BigDecimal monthPayback) {
		this.monthPayback = monthPayback;
	}

	public BigDecimal getFinalAmount() {
		return finalAmount;
	}

	public void setFinalAmount(BigDecimal finalAmount) {
		this.finalAmount = finalAmount;
	}

	public BigDecimal getMobServ() {
		return mobServ;
	}

	public void setMobServ(BigDecimal mobServ) {
		this.mobServ = mobServ;
	}

	public BigDecimal getCardServ() {
		return cardServ;
	}

	public void setCardServ(BigDecimal cardServ) {
		this.cardServ = cardServ;
	}

	public BigDecimal getSfServ() {
		return sfServ;
	}

	public void setSfServ(BigDecimal sfServ) {
		this.sfServ = sfServ;
	}

	public BigDecimal getZxServ() {
		return zxServ;
	}

	public void setZxServ(BigDecimal zxServ) {
		this.zxServ = zxServ;
	}

	public BigDecimal getCuServ() {
		return cuServ;
	}

	public void setCuServ(BigDecimal cuServ) {
		this.cuServ = cuServ;
	}

	public BigDecimal getWhServ() {
		return whServ;
	}

	public void setWhServ(BigDecimal whServ) {
		this.whServ = whServ;
	}

	public BigDecimal getInfoServ() {
		return infoServ;
	}

	public void setInfoServ(BigDecimal infoServ) {
		this.infoServ = infoServ;
	}

	public BigDecimal getEverMoth() {
		return everMoth;
	}

	public void setEverMoth(BigDecimal everMoth) {
		this.everMoth = everMoth;
	}

	public BigDecimal getPerMonthPay() {
		return perMonthPay;
	}

	public void setPerMonthPay(BigDecimal perMonthPay) {
		this.perMonthPay = perMonthPay;
	}

	public BigDecimal getServiceMonthPay() {
		return serviceMonthPay;
	}

	public void setServiceMonthPay(BigDecimal serviceMonthPay) {
		this.serviceMonthPay = serviceMonthPay;
	}

	public BigDecimal getjCopePremium() {
		return jCopePremium;
	}

	public void setjCopePremium(BigDecimal jCopePremium) {
		this.jCopePremium = jCopePremium;
	}

	public BigDecimal getjInsuredAmount() {
		return jInsuredAmount;
	}

	public void setjInsuredAmount(BigDecimal jInsuredAmount) {
		this.jInsuredAmount = jInsuredAmount;
	}

	public BigDecimal getjMerPermium() {
		return jMerPermium;
	}

	public void setjMerPermium(BigDecimal jMerPermium) {
		this.jMerPermium = jMerPermium;
	}

	public BigDecimal getwCopePremium() {
		return wCopePremium;
	}

	public void setwCopePremium(BigDecimal wCopePremium) {
		this.wCopePremium = wCopePremium;
	}

	public BigDecimal getwInsuredAmount() {
		return wInsuredAmount;
	}

	public void setwInsuredAmount(BigDecimal wInsuredAmount) {
		this.wInsuredAmount = wInsuredAmount;
	}

	public BigDecimal getwMerPermium() {
		return wMerPermium;
	}

	public void setwMerPermium(BigDecimal wMerPermium) {
		this.wMerPermium = wMerPermium;
	}

	public BigDecimal getTotMoth() {
		return totMoth;
	}

	public void setTotMoth(BigDecimal totMoth) {
		this.totMoth = totMoth;
	}

}
