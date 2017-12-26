package com.hengyuan.hicash.entity.app;



public class CustPayAccount  {
      
	/** 客户类型 */
	public String custType; 

	/** 城市 */
	public String city;
	
	/** 是否启用 */
	public Boolean status;
	
	/** 省份 */
	public String  province;
	
	/** 付款账户 */
	public String  payUsername;//
	
	/** 地址 */
	public String address;	
	
	/** 产品类型 */
	public String proType;	

	/** 协议模板名称 */
	public String modeName;
	
	/** 
	 * 	支付方式
	 * 	FIRST 比例首付支付
	 * 	NORMAL 正常首付
	 *  */
	public String payType;
	
	/**
	 * 是否开放给5i5dai投资
	 */
	public Integer investFlag;
	
	/**民族*/
	public String nation;

	public String getCustType() {
		return custType;
	}

	public void setCustType(String custType) {
		this.custType = custType;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPayUsername() {
		return payUsername;
	}

	public void setPayUsername(String payUsername) {
		this.payUsername = payUsername;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getModeName() {
		return modeName;
	}

	public void setModeName(String modeName) {
		this.modeName = modeName;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public Integer getInvestFlag() {
		return investFlag;
	}

	public void setInvestFlag(Integer investFlag) {
		this.investFlag = investFlag;
	}

	public String getNation() {
		return nation;
	}

	public void setNation(String nation) {
		this.nation = nation;
	}
	
	
}
