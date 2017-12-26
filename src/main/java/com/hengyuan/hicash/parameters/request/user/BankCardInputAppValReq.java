package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 根据订单号修改绑定的代扣银行卡
 * 
 * @author Lihua.Ren
 * @createDate 2015-12-04
 *
 */
public class BankCardInputAppValReq extends RequestSequence {
	private static final long serialVersionUID = -546865098483075661L;

	/** 交易日期 yyyyMMdd */
	private String transDate;
	/** 交易时间 HHmmss */
	private String transTime;
	/** 流水号 */
	private String serialNo;
	/** 商户号 测试：530000000000015 */
	private String merId;
	/** 实名认证商户：亨元金融 */
	private String merName;
	/** 银行帐号 */
	private String accNo;
	/** 银行户名 */
	private String accName;
	/** 证件类型 –身份证：值为：01 */
	private String certType;
	/** 证件号 */
	private String certNo;
	/** 手机号 */
	private String mobile;
	/** 银行编码 比如：CEB，光大银行 */
	private String BankNo;
	/** 验证套餐代码 值：100060 */
	private String checkCode;
	/** 用户名 */
	private String userName;
	
	private String relaArea;
	
	private String relaCity;
	
	private String relaProv;

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public String getTransTime() {
		return transTime;
	}

	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getMerId() {
		return merId;
	}

	public void setMerId(String merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getCertNo() {
		return certNo;
	}

	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getBankNo() {
		return BankNo;
	}

	public void setBankNo(String bankNo) {
		BankNo = bankNo;
	}

	public String getCheckCode() {
		return checkCode;
	}

	public void setCheckCode(String checkCode) {
		this.checkCode = checkCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRelaArea() {
		return relaArea;
	}

	public void setRelaArea(String relaArea) {
		this.relaArea = relaArea;
	}

	public String getRelaCity() {
		return relaCity;
	}

	public void setRelaCity(String relaCity) {
		this.relaCity = relaCity;
	}

	public String getRelaProv() {
		return relaProv;
	}

	public void setRelaProv(String relaProv) {
		this.relaProv = relaProv;
	}
	
	

}
