package com.hengyuan.hicash.parameters.response.user;

import com.hengyuan.hicash.parameters.response.ParmResponse;

/**
 * hicash手机端学生提现申请3查询返回信息
 * 
 * @author lihua.Ren
 * @create date 2015-05-27
 *
 */
public class StuApp3Resp  extends ParmResponse{

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

	/**
	 * @return the nameSala
	 */
	public String getNameSala() {
		return nameSala;
	}

	/**
	 * @param nameSala the nameSala to set
	 */
	public void setNameSala(String nameSala) {
		this.nameSala = nameSala;
	}

	/**
	 * @return the tionSala
	 */
	public String getTionSala() {
		return tionSala;
	}

	/**
	 * @param tionSala the tionSala to set
	 */
	public void setTionSala(String tionSala) {
		this.tionSala = tionSala;
	}

	/**
	 * @return the tionSalaName
	 */
	public String getTionSalaName() {
		return tionSalaName;
	}

	/**
	 * @param tionSalaName the tionSalaName to set
	 */
	public void setTionSalaName(String tionSalaName) {
		this.tionSalaName = tionSalaName;
	}

	/**
	 * @return the mobileSala
	 */
	public String getMobileSala() {
		return mobileSala;
	}

	/**
	 * @param mobileSala the mobileSala to set
	 */
	public void setMobileSala(String mobileSala) {
		this.mobileSala = mobileSala;
	}

	/**
	 * @return the workUnitsSala
	 */
	public String getWorkUnitsSala() {
		return workUnitsSala;
	}

	/**
	 * @param workUnitsSala the workUnitsSala to set
	 */
	public void setWorkUnitsSala(String workUnitsSala) {
		this.workUnitsSala = workUnitsSala;
	}

	/**
	 * @return the addressSala
	 */
	public String getAddressSala() {
		return addressSala;
	}

	/**
	 * @param addressSala the addressSala to set
	 */
	public void setAddressSala(String addressSala) {
		this.addressSala = addressSala;
	}

	/**
	 * @return the relaName
	 */
	public String getRelaName() {
		return relaName;
	}

	/**
	 * @param relaName the relaName to set
	 */
	public void setRelaName(String relaName) {
		this.relaName = relaName;
	}

	/**
	 * @return the relation
	 */
	public String getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(String relation) {
		this.relation = relation;
	}

	/**
	 * @return the relationName
	 */
	public String getRelationName() {
		return relationName;
	}

	/**
	 * @param relationName the relationName to set
	 */
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}

//	/**
//	 * @return the relaWorkUnits
//	 */
//	public String getRelaWorkUnits() {
//		return relaWorkUnits;
//	}
//
//	/**
//	 * @param relaWorkUnits the relaWorkUnits to set
//	 */
//	public void setRelaWorkUnits(String relaWorkUnits) {
//		this.relaWorkUnits = relaWorkUnits;
//	}

	/**
	 * @return the relaMobile
	 */
	public String getRelaMobile() {
		return relaMobile;
	}

	/**
	 * @param relaMobile the relaMobile to set
	 */
	public void setRelaMobile(String relaMobile) {
		this.relaMobile = relaMobile;
	}

//	/**
//	 * @return the relaAddress
//	 */
//	public String getRelaAddress() {
//		return relaAddress;
//	}
//
//	/**
//	 * @param relaAddress the relaAddress to set
//	 */
//	public void setRelaAddress(String relaAddress) {
//		this.relaAddress = relaAddress;
//	}

	/**
	 * @return the custType
	 */
	public String getCustType() {
		return custType;
	}

	/**
	 * @param custType the custType to set
	 */
	public void setCustType(String custType) {
		this.custType = custType;
	}

	/**
	 * @return the custTypeName
	 */
	public String getCustTypeName() {
		return custTypeName;
	}

	/**
	 * @param custTypeName the custTypeName to set
	 */
	public void setCustTypeName(String custTypeName) {
		this.custTypeName = custTypeName;
	}

}
