package com.hengyuan.hicash.entity.app;

import java.util.Date;

public class NoahValidIdentityEntity {

	// 主键id
	private Integer id;

	// 创建时间
	private Date createTime;

	// 银行卡验证时间
	private Date validDateTime;

	// 银行卡号
	private String bankCardNum;

	// 用户真实姓名
	private String realName;

	// 银行编码
	private String bankCode;

	// 用户身份证号码
	private String identityNo;

	// 用户手机号码
	private String mobleNo;

	// 银行返回验证结果编码
	private String bankReturnCode;

	// 银行返回验证结果描述
	private String bankReturnMsg;

	// 自定义验证结果编码
	private String respCode;

	// 自定义验证结果描述
	private String respMsg;

	// 认证流水号
	private String serialNo;

	// 用户名
	private String userName;

	// 银行名称
	private String bankName;

	// 银行返回详细编码
	private String tradeCode;

	// 银行返回详细描述
	private String tradeDesc;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getValidDateTime() {
		return validDateTime;
	}

	public void setValidDateTime(Date validDateTime) {
		this.validDateTime = validDateTime;
	}

	public String getBankCardNum() {
		return bankCardNum;
	}

	public void setBankCardNum(String bankCardNum) {
		this.bankCardNum = bankCardNum;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getIdentityNo() {
		return identityNo;
	}

	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}

	public String getMobleNo() {
		return mobleNo;
	}

	public void setMobleNo(String mobleNo) {
		this.mobleNo = mobleNo;
	}

	public String getBankReturnCode() {
		return bankReturnCode;
	}

	public void setBankReturnCode(String bankReturnCode) {
		this.bankReturnCode = bankReturnCode;
	}

	public String getBankReturnMsg() {
		return bankReturnMsg;
	}

	public void setBankReturnMsg(String bankReturnMsg) {
		this.bankReturnMsg = bankReturnMsg;
	}

	public String getRespCode() {
		return respCode;
	}

	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}

	public String getSerialNo() {
		return serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getTradeCode() {
		return tradeCode;
	}

	public void setTradeCode(String tradeCode) {
		this.tradeCode = tradeCode;
	}

	public String getTradeDesc() {
		return tradeDesc;
	}

	public void setTradeDesc(String tradeDesc) {
		this.tradeDesc = tradeDesc;
	}

}
