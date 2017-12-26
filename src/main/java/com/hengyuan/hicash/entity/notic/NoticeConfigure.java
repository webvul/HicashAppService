package com.hengyuan.hicash.entity.notic;

import com.hengyuan.hicash.dao.dict.BusinessType;

/**
 * 发送短信公共类
 * 
 * @author Cary.Liu
 * @createDate 2015-12-23
 *
 */
public class NoticeConfigure {
	
	private BusinessType bussId;
	private String bussName;
	private Boolean smsFlag;
	private String smsTemplate;
	private String smsSendType;
	private Boolean emailFlag;
	private String emailTemplate;
	private String emailSendType;
	
	private Boolean stationLetterFlag;
	private String stationLetterTemplate;
	private String stationLetterSendType;
	public BusinessType getBussId() {
		return bussId;
	}
	public void setBussId(BusinessType bussId) {
		this.bussId = bussId;
	}
	public String getBussName() {
		return bussName;
	}
	public void setBussName(String bussName) {
		this.bussName = bussName;
	}
	public Boolean getSmsFlag() {
		return smsFlag;
	}
	public void setSmsFlag(Boolean smsFlag) {
		this.smsFlag = smsFlag;
	}
	public String getSmsTemplate() {
		return smsTemplate;
	}
	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate;
	}
	public String getSmsSendType() {
		return smsSendType;
	}
	public void setSmsSendType(String smsSendType) {
		this.smsSendType = smsSendType;
	}
	public Boolean getEmailFlag() {
		return emailFlag;
	}
	public void setEmailFlag(Boolean emailFlag) {
		this.emailFlag = emailFlag;
	}
	public String getEmailTemplate() {
		return emailTemplate;
	}
	public void setEmailTemplate(String emailTemplate) {
		this.emailTemplate = emailTemplate;
	}
	public String getEmailSendType() {
		return emailSendType;
	}
	public void setEmailSendType(String emailSendType) {
		this.emailSendType = emailSendType;
	}
	public Boolean getStationLetterFlag() {
		return stationLetterFlag;
	}
	public void setStationLetterFlag(Boolean stationLetterFlag) {
		this.stationLetterFlag = stationLetterFlag;
	}
	public String getStationLetterTemplate() {
		return stationLetterTemplate;
	}
	public void setStationLetterTemplate(String stationLetterTemplate) {
		this.stationLetterTemplate = stationLetterTemplate;
	}
	public String getStationLetterSendType() {
		return stationLetterSendType;
	}
	public void setStationLetterSendType(String stationLetterSendType) {
		this.stationLetterSendType = stationLetterSendType;
	}
	
	
	
}
