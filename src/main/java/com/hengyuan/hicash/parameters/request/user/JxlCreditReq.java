package com.hengyuan.hicash.parameters.request.user;

import com.hengyuan.hicash.parameters.request.RequestSequence;

/** 
 * @author meng.zhang 
 * 2017年1月9日 下午3:23:15
 * 类说明  运营商认证参数封装
 */
public class JxlCreditReq extends RequestSequence{

	private static final long serialVersionUID = 1L;
	private String args;
	
	private String mobile;
	private String password;
	private String message;
	private String time;
	private String seq_no;
	private String website;
	private String currentMsgType;
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getCurrentMsgType() {
		return currentMsgType;
	}
	public void setCurrentMsgType(String currentMsgType) {
		this.currentMsgType = currentMsgType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSeq_no() {
		return seq_no;
	}
	public void setSeq_no(String seq_no) {
		this.seq_no = seq_no;
	}
	
	
}
