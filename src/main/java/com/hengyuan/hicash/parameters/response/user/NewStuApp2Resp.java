package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请2返回参数
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class NewStuApp2Resp extends ParmResponse {

	/** 直系亲属姓名 */
	private String nameSala;

	/** 直系亲属关系 */
	private String tionSala;

	private String tionSalaName;

	/** 直系亲属手机 */
	private String mobileSala;

	/** 直系亲属工作单位 */
	private String workUnitsSala;

	/** 直系亲属地址 */
	private String addressSala;

	/** 紧急联系人姓名 */
	private String relaName;

	/** 紧急联系人关系 */
	private String relation;

	private String relationName;

//	/** 紧急联系人工作单位 */
//	private String relaWorkUnits;

	/** 紧急联系人手机 */
	private String relaMobile;

//	/** 紧急联系人地址 */
//	private String relaAddress;

	/** 客户类型 */
	private String custType;

	/** 客户类型名称 */
	private String custTypeName;
	
	/** 配偶姓名 */
	private String spouseName;
	/** 配偶手机号码 */
	private String spouseMobile;

	public String getNameSala() {
		return nameSala;
	}

	public void setNameSala(String nameSala) {
		this.nameSala = nameSala;
	}

	public String getTionSala() {
		return tionSala;
	}

	public void setTionSala(String tionSala) {
		this.tionSala = tionSala;
	}

	public String getTionSalaName() {
		return tionSalaName;
	}

	public void setTionSalaName(String tionSalaName) {
		this.tionSalaName = tionSalaName;
	}

	public String getMobileSala() {
		return mobileSala;
	}

	public void setMobileSala(String mobileSala) {
		this.mobileSala = mobileSala;
	}

	public String getWorkUnitsSala() {
		return workUnitsSala;
	}

	public void setWorkUnitsSala(String workUnitsSala) {
		this.workUnitsSala = workUnitsSala;
	}

	public String getAddressSala() {
		return addressSala;
	}

	public void setAddressSala(String addressSala) {
		this.addressSala = addressSala;
	}

	public String getRelaName() {
		return relaName;
	}

	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}

	public String getRelationName() {
		return relationName;
	}

	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

	public String getRelaMobile() {
		return relaMobile;
	}

	public void setRelaMobile(String relaMobile) {
		this.relaMobile = relaMobile;
	}

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCustTypeName() {
		return custTypeName;
	}

	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

	public String getSpouseName() {
		return spouseName;
	}

	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}

	public String getSpouseMobile() {
		return spouseMobile;
	}

	public void setSpouseMobile(String spouseMobile) {
		this.spouseMobile = spouseMobile;
	}

	
	

}
