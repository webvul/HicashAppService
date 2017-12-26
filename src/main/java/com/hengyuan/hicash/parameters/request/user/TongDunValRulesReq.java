package com.hengyuan.hicash.parameters.request.user;

import com.google.gson.Gson;
import com.hengyuan.hicash.parameters.request.RequestSequence;

/**
 * 同盾接口验证客户黑名单，客户身份证号码、手机号码、邮箱、qq
 * 
 * @author lihua.Ren
 * @create date 2015-11-10
 *
 */
public class TongDunValRulesReq extends RequestSequence{

	private static final long serialVersionUID = -1119118923016726991L;
	private String token_id;
	/* 姓名 */
	private String account_name;
	
	/* 身份证号 */
	private String id_number;
	
	/* 手机号码 */
	private String account_mobile;
	private String ip_address;
	/* 邮箱 */
	private String account_email;
	
	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	/**
	 * @return the account_name
	 */
	public String getAccount_name() {
		return account_name;
	}
	/**
	 * @param account_name the account_name to set
	 */
	public void setAccount_name(String account_name) {
		this.account_name = account_name;
	}
	/**
	 * @return the id_number
	 */
	public String getId_number() {
		return id_number;
	}
	/**
	 * @param id_number the id_number to set
	 */
	public void setId_number(String id_number) {
		this.id_number = id_number;
	}
	/**
	 * @return the account_mobile
	 */
	public String getAccount_mobile() {
		return account_mobile;
	}
	/**
	 * @param account_mobile the account_mobile to set
	 */
	public void setAccount_mobile(String account_mobile) {
		this.account_mobile = account_mobile;
	}
	/**
	 * @return the ip_address
	 */
	public String getIp_address() {
		return ip_address;
	}
	/**
	 * @param ip_address the ip_address to set
	 */
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	/**
	 * @return the account_email
	 */
	public String getAccount_email() {
		return account_email;
	}
	/**
	 * @param account_email the account_email to set
	 */
	public void setAccount_email(String account_email) {
		this.account_email = account_email;
	}
	/**
	 * @return the token_id
	 */
	public String getToken_id() {
		return token_id;
	}
	/**
	 * @param token_id the token_id to set
	 */
	public void setToken_id(String token_id) {
		this.token_id = token_id;
	}
	

	
}
