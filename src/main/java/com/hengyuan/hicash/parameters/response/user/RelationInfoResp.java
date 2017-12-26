package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * 
 * @author fish
 *
 * @date 2017年1月11日 上午9:38:22
 */
public class RelationInfoResp extends ParmResponse {

	private String userName;

	private String marital_status;

	private String immediate_name;

	private String immediate_relation;

	private String immediate_mobile;

	private String emergency_name;

	private String emergency_relation;

	private String emergency_mobile;

	private String spouse_name;

	private String spouse_mobile;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMarital_status() {
		return marital_status;
	}

	public void setMarital_status(String marital_status) {
		this.marital_status = marital_status;
	}

	public String getImmediate_name() {
		return immediate_name;
	}

	public void setImmediate_name(String immediate_name) {
		this.immediate_name = immediate_name;
	}

	public String getImmediate_relation() {
		return immediate_relation;
	}

	public void setImmediate_relation(String immediate_relation) {
		this.immediate_relation = immediate_relation;
	}

	public String getImmediate_mobile() {
		return immediate_mobile;
	}

	public void setImmediate_mobile(String immediate_mobile) {
		this.immediate_mobile = immediate_mobile;
	}

	public String getEmergency_name() {
		return emergency_name;
	}

	public void setEmergency_name(String emergency_name) {
		this.emergency_name = emergency_name;
	}

	public String getEmergency_relation() {
		return emergency_relation;
	}

	public void setEmergency_relation(String emergency_relation) {
		this.emergency_relation = emergency_relation;
	}

	public String getEmergency_mobile() {
		return emergency_mobile;
	}

	public void setEmergency_mobile(String emergency_mobile) {
		this.emergency_mobile = emergency_mobile;
	}

	public String getSpouse_name() {
		return spouse_name;
	}

	public void setSpouse_name(String spouse_name) {
		this.spouse_name = spouse_name;
	}

	public String getSpouse_mobile() {
		return spouse_mobile;
	}

	public void setSpouse_mobile(String spouse_mobile) {
		this.spouse_mobile = spouse_mobile;
	}

}
